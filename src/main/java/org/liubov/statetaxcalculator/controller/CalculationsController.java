package org.liubov.statetaxcalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.dto.FillingParametersDTO;
import org.liubov.statetaxcalculator.repository.FillingParametersRepository;
import org.liubov.statetaxcalculator.service.FillingParametersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@Slf4j
public class CalculationsController {

    private FillingParametersService fillingParametersService;

    public CalculationsController(FillingParametersService fillingParametersService) {
        this.fillingParametersService = fillingParametersService;
    }

    @GetMapping
    public String getList(Model model) {
        List<FillingParametersDTO> fillingParametersDTOList = fillingParametersService.findByUserId(1);

        model.addAttribute("fillingParametersDTOList", fillingParametersDTOList);

        return "calculations";
    }
}
