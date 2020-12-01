package br.com.robertomassoni.xyinc.controller.api;

import br.com.robertomassoni.xyinc.controller.request.UserRequest;
import br.com.robertomassoni.xyinc.dto.model.TokenDto;
import br.com.robertomassoni.xyinc.dto.response.Response;
import br.com.robertomassoni.xyinc.security.SecurityConstants;
import br.com.robertomassoni.xyinc.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private TokenService tokenService;
 
    @PostMapping
    public Response auth(@RequestBody UserRequest userRequest) {
        return Response.ok().setContent(getToken(userRequest));

    }
 
    private TokenDto getToken(UserRequest userRequest) {        
        UsernamePasswordAuthenticationToken loginInfo = userRequest.converter();
        Authentication auth = authManager.authenticate(loginInfo);
        String token = tokenService.generateToken(auth);
        return new TokenDto(token, SecurityConstants.TOKEN_PREFIX);
    }
}
