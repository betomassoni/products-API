package br.com.robertomassoni.xyinc.repository;

import br.com.robertomassoni.xyinc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
        
    public User findByEmail(String email);
}
