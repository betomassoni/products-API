package br.com.robertomassoni.xyinc.controller.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserRequest {

    private String user;
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(this.user, this.password);
    }
}
