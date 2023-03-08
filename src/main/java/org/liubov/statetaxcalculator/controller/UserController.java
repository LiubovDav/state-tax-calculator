package org.liubov.statetaxcalculator.controller;

import org.liubov.statetaxcalculator.dto.UserDTO;
import org.liubov.statetaxcalculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/save")
    public void saveUser() {
        UserDTO user1 = new UserDTO();
        user1.setName("Ivan");
        user1.setEmail("fgdr@fre.cf");
        user1.setPassword("wert");

        userService.save(user1);

        UserDTO user2 = new UserDTO();
        user2.setName("Olga");
        user2.setEmail("olga@gmail.com");
        user2.setPassword("wegjgjkgjhkgjhrt");

        userService.save(user2);
    }
}
