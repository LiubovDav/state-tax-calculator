package org.liubov.statetaxcalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.model.User;
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

    @GetMapping("/")
    public String showHomePage(Model model) {
        return "home";
    }

    @GetMapping("/sign_up")
    public String signUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "sign_up";
    }

    @PostMapping("/sign_up_submit")
    public String signUpSubmit(@ModelAttribute("user") User user) {
        log.info("User = " + user);

        // todo: save the User to the DB

        return "sign_up_success";
    }

    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login_submit")
    public String loginSubmit(@ModelAttribute("user") User user) {
        log.info("User = " + user);

        // todo: validate user email and password

        return "login_success";
    }
}
