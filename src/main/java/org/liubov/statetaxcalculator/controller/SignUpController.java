package org.liubov.statetaxcalculator.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.dto.UserDTO;
import org.liubov.statetaxcalculator.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign_up")
@Slf4j
public class SignUpController {

    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSignUpPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);

        return "sign_up";
    }

    @PostMapping("/submit")
    public String signUpSubmit(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                               BindingResult bindingResult, Model model) {
        log.info("User = " + userDTO);

        UserDTO existingUserDTO = userService.findByEmail(userDTO.getEmail());

        if (existingUserDTO != null && existingUserDTO.getEmail() != null && !existingUserDTO.getEmail().isEmpty()) {
            bindingResult.rejectValue("email", null, "User with that email already exists");
        }

        if (userDTO.getPassword() != null && userDTO.getPassword2() != null && !userDTO.getPassword().equals(userDTO.getPassword2())) {
            bindingResult.rejectValue("password", null, "Password and confirm password do not match");
            bindingResult.rejectValue("password2", null, "Password and confirm password do not match");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "sign_up";
        }

        userService.save(userDTO);

        return "redirect:/sign_up?success";
    }
}
