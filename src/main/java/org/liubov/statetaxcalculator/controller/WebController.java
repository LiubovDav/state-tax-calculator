package org.liubov.statetaxcalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.dto.UserDTO;
import org.liubov.statetaxcalculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Slf4j
public class WebController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showHomePage(Model model) {
        return "home";
    }

    @GetMapping("/sign_up")
    public String signUp(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "sign_up";
    }

    @PostMapping("/sign_up_submit")
    public String signUpSubmit(@ModelAttribute("user") UserDTO userDTO) {
        log.info("User = " + userDTO);

        userService.save(userDTO);

        return "sign_up_success";
    }

    @GetMapping("/login")
    public String login(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "login";
    }

    @PostMapping("/login_submit")
    public String loginSubmit(@ModelAttribute("user") UserDTO userDTO) {
        log.info("User = " + userDTO);

        if (userService.validate(userDTO.getEmail(), userDTO.getPassword())) {
            return "login_success";
        } else {
            // the password is wrong
            // todo: add error details for UI
            return "home";
        }
    }
}
