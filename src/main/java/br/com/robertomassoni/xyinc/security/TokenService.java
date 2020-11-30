package br.com.robertomassoni.xyinc.security;

import br.com.robertomassoni.xyinc.controller.request.UserRequest;
import br.com.robertomassoni.xyinc.dto.model.TokenDto;
import br.com.robertomassoni.xyinc.model.User;
import br.com.robertomassoni.xyinc.util.DateUtil;
import java.util.Date;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
public class TokenService {
    
    @Autowired
    private AuthenticationManager authManager;
    
    
    public String generateToken(Authentication authentication) {
        User userLogged = (User) authentication.getPrincipal();
        Date today = DateUtil.today();
        Date expirationDate = new Date(today.getTime() + SecurityConstants.EXPIRATION_TIME);

        return Jwts.builder()
                .setIssuer("Xy Inc API")
                .setSubject(userLogged.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.getSubject());
    }
    
    public TokenDto getToken(UserRequest userRequest) {        
        UsernamePasswordAuthenticationToken loginInfo = userRequest.converter();
        Authentication auth = authManager.authenticate(loginInfo);
        String token = this.generateToken(auth);
        return new TokenDto(token, SecurityConstants.TOKEN_PREFIX);
    }
}
