package org.liubov.statetaxcalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.dto.FilingParametersDTO;
import org.liubov.statetaxcalculator.service.FilingParametersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/calculations")
@Slf4j
public class CalculationsController {

    private FilingParametersService filingParametersService;

    public CalculationsController(FilingParametersService filingParametersService) {
        this.filingParametersService = filingParametersService;
    }

    @GetMapping
    public String getList(Model model) {
        List<FilingParametersDTO> filingParametersDTOList = filingParametersService.findByUserId(1);

        model.addAttribute("filingParametersDTOList", filingParametersDTOList);

        return "calculations";
    }
}
