package org.liubov.statetaxcalculator.controller.api;

import org.liubov.statetaxcalculator.dto.UserDTO;
import org.liubov.statetaxcalculator.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> list() {
        return userService.findAll();
    }

    @PostMapping("/save_test")
    public void saveUser() {
        UserDTO user1 = new UserDTO();
        user1.setName("John");
        user1.setEmail("john@gmail.com");
        user1.setPassword("hgasjhdgajshdgj");

        userService.save(user1);

        UserDTO user2 = new UserDTO();
        user2.setName("Anna");
        user2.setEmail("anna@gmail.com");
        user2.setPassword("hfdhagsjhdgajshdg");

        userService.save(user2);
    }
}
