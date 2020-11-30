package br.com.robertomassoni.xyinc.service;

import br.com.robertomassoni.xyinc.enumerator.EntityType;
import br.com.robertomassoni.xyinc.enumerator.ExceptionType;
import br.com.robertomassoni.xyinc.exception.XyincException;
import br.com.robertomassoni.xyinc.model.User;
import br.com.robertomassoni.xyinc.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public User findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return user.get();
//            return UserMapper.toUserDto(user.get());
        }
        throw XyincException.throwException(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, null);
    }

    @Override
    public User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
//            return UserMapper.toUserDto(user.get());
        }
        throw XyincException.throwException(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, null);
    }

}
