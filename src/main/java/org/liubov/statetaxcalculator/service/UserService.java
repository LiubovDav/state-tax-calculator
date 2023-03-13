package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    void save(UserDTO userDTO);

    boolean validate(String email, String password);

    UserDTO findByEmail(String email);
}
