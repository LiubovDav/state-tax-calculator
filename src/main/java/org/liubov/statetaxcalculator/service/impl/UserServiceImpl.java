package org.liubov.statetaxcalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.dto.UserDTO;
import org.liubov.statetaxcalculator.mapper.UserMapper;
import org.liubov.statetaxcalculator.model.User;
import org.liubov.statetaxcalculator.repository.UserRepository;
import org.liubov.statetaxcalculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(userMapper.convertToUser(userDTO));
        log.info("User was successfully saved");
    }

    @Override
    public boolean validate(String email, String password) {
        User user = userRepository.findByEmail(email).get();

        if (user == null) {
            return false;
        }

        return passwordEncoder.matches(password, user.getPassword());
    }
}
