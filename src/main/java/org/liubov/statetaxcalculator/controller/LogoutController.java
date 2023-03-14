package org.liubov.statetaxcalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
@Slf4j
public class LogoutController {

    @GetMapping
    public String showLogoutPage(Model model) {



        return "home";
    }
}
