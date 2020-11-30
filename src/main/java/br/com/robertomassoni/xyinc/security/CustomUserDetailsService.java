package br.com.robertomassoni.xyinc.security;

import br.com.robertomassoni.xyinc.enumerator.EntityType;
import br.com.robertomassoni.xyinc.enumerator.ExceptionType;
import br.com.robertomassoni.xyinc.exception.XyincException;
import br.com.robertomassoni.xyinc.model.User;
import br.com.robertomassoni.xyinc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String email) {
        User userDto = userService.findUserByEmail(email);
        if (userDto != null) {
            return userDto;
        } 
        throw XyincException.throwException(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, null);
    }    

}
