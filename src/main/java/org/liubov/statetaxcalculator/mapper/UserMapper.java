package org.liubov.statetaxcalculator.mapper;

import org.liubov.statetaxcalculator.dto.UserDTO;
import org.liubov.statetaxcalculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        // encode password
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        user.setPassword(user.getPassword());
        user.setCreatedOn(userDTO.getCreatedOn());
        user.setUpdatedOn(userDTO.getUpdatedOn());

        return user;
    }

    public UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setCreatedOn(user.getCreatedOn());
        userDTO.setUpdatedOn(user.getUpdatedOn());

        return userDTO;
    }

    public List<User> toUserList(List<UserDTO> userDTOList) {
        List<User> userList = new ArrayList<>();

        for (UserDTO userDTO: userDTOList) {
            userList.add(toUser(userDTO));
        }

        return userList;
    }

    public List<UserDTO> toUserDTOList(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user: userList) {
            userDTOList.add(toUserDTO(user));
        }

        return userDTOList;
    }
}
