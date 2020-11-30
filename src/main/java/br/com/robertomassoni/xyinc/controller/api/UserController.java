package br.com.robertomassoni.xyinc.controller.api;

import br.com.robertomassoni.xyinc.controller.request.UserRequest;
import br.com.robertomassoni.xyinc.dto.response.Response;
import br.com.robertomassoni.xyinc.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private TokenService tokenService;
 
    @PostMapping
    public Response auth(@RequestBody UserRequest userRequest) {
        return Response.ok().setContent(tokenService.getToken(userRequest));

    }
 
}
