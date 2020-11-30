package br.com.robertomassoni.xyinc.service;

import br.com.robertomassoni.xyinc.model.User;


public interface UserService {
    
    User findUserByEmail(String email);
    
    User findById(int id);
}
