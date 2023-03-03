package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.dto.UserDTO;

public interface UserService {
    void save(UserDTO userDTO);

    boolean validate(String email, String password);
}
