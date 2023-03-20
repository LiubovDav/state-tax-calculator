package org.liubov.statetaxcalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.dto.UserDTO;
import org.liubov.statetaxcalculator.mapper.UserMapper;
import org.liubov.statetaxcalculator.model.User;
import org.liubov.statetaxcalculator.repository.UserRepository;
import org.liubov.statetaxcalculator.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
//        this.userMapper = userMapper;
//        this.userRepository = userRepository;
//    }

    @Override
    public List<UserDTO> findAll() {
        return userMapper.toUserDTOList(userRepository.findAll());
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(userMapper.toUser(userDTO));
        log.info("User was successfully saved");
    }

    @Override
    public boolean validate(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return false;
        }

        return passwordEncoder.matches(password, user.getPassword());
//        return true;
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userMapper.toUserDTO(userRepository.findByEmail(email));
    }
}
