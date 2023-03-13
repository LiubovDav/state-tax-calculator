package org.liubov.statetaxcalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.dto.UserDTO;
import org.liubov.statetaxcalculator.service.UserService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@Slf4j
public class AccountController {

    private UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAccountPage(Model model) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John");
        userDTO.setEmail("john@gmail.com");
        model.addAttribute("userDTO", userDTO);

        return "account";
    }

    @PostMapping("/submit")
    public String updateAccount(@ModelAttribute("userDTO") UserDTO userDTO,
                                BindingResult bindingResult, Model model) {
//        UserDTO userDTO = userService.findByEmail();
//
//        userService.save(userDTO);

        model.addAttribute("userDTO", userDTO);

        return "redirect:/account?success";
    }

    @PostMapping("/submitNewPassword")
    public String updateAccountPassword(@ModelAttribute("userDTO") UserDTO userDTO,
                                BindingResult bindingResult, Model model) {

//        UserDTO userDTO = userService.findByEmail();
//
//        userService.save(userDTO);

        model.addAttribute("userDTO", userDTO);

        return "redirect:/account?successNewPassword";
    }
}
