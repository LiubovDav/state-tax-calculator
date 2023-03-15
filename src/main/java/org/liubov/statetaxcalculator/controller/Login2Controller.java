package org.liubov.statetaxcalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.config.AppConstants;
import org.liubov.statetaxcalculator.dto.FilingParametersDTO;
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
@RequestMapping("/login2")
@Slf4j
public class Login2Controller {

    private UserService userService;

    public Login2Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showLoginPage(Model model) {
//        UserDTO userDTO = (UserDTO) model.getAttribute(("userDTO"));
//        if (userDTO == null) {
//            userDTO = new UserDTO();
//        }
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);

        FilingParametersDTO filingParametersDTO = new FilingParametersDTO();
        model.addAttribute("filingParametersDTO", filingParametersDTO);
        model.addAttribute("listYear", AppConstants.YEAR_LIST);
        model.addAttribute("listState", AppConstants.STATE_LIST);
        model.addAttribute("listFilingStatus", AppConstants.FILING_STATUS_LIST);

        return "login";
    }

    @PostMapping("/submit")
    public String loginSubmit(@ModelAttribute("userDTO") UserDTO userDTO,
                              BindingResult bindingResult, Model model) {
        log.info("User = " + userDTO);

        model.addAttribute("userDTO", userDTO);

        if (userDTO.getEmail() == null || userDTO.getEmail().equals("")) {
            bindingResult.rejectValue("email", null, "Email should not be empty");
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().equals("")) {
            bindingResult.rejectValue("password", null, "Password should not be empty");
        }

        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty() &&
                userDTO.getPassword() != null && !userDTO.getPassword().isEmpty() &&
                !userService.validate(userDTO.getEmail(), userDTO.getPassword())) {
            bindingResult.rejectValue("password", null, "Wrong login/password");
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/login2";
        }

        FilingParametersDTO filingParametersDTO = new FilingParametersDTO();
        model.addAttribute("filingParametersDTO", filingParametersDTO);
        model.addAttribute("listYear", AppConstants.YEAR_LIST);
        model.addAttribute("listState", AppConstants.STATE_LIST);
        model.addAttribute("listFilingStatus", AppConstants.FILING_STATUS_LIST);

        return "home";
    }
}
