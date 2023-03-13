package org.liubov.statetaxcalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.config.AppConstants;
import org.liubov.statetaxcalculator.dto.FillingParametersDTO;
import org.liubov.statetaxcalculator.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {

    @GetMapping
    public String showHomePage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);

        FillingParametersDTO fillingParametersDTO = new FillingParametersDTO();
        model.addAttribute("fillingParametersDTO", fillingParametersDTO);
        model.addAttribute("listYear", AppConstants.YEAR_LIST);
        model.addAttribute("listState", AppConstants.STATE_LIST);
        model.addAttribute("listFillingStatus", AppConstants.FILLING_STATUS_LIST);

        return "home";
    }
}
