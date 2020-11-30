package br.com.robertomassoni.xyinc.dto.mapper;

import br.com.robertomassoni.xyinc.dto.model.UserDto;
import br.com.robertomassoni.xyinc.model.User;
import java.util.ArrayList;
import java.util.List;


public class UserMapper {
    
    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRoleList((List)userDto.getRoles());
        return user;
    }
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(null);
        return userDto;
    }
    
    public static List<UserDto> toCategoryDto(List<User> userList) {
        ArrayList<UserDto> userDtoList = new ArrayList();
        
        for (User user : userList) {
            userDtoList.add(toUserDto(user));
        }
        
        return userDtoList;
    }
}
