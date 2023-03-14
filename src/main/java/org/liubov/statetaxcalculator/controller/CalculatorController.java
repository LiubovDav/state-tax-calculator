package org.liubov.statetaxcalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.config.AppConstants;
import org.liubov.statetaxcalculator.dto.FillingParametersDTO;
import org.liubov.statetaxcalculator.exception.IncomeTaxCalculatorException;
import org.liubov.statetaxcalculator.service.FillingParametersService;
import org.liubov.statetaxcalculator.service.TaxCalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/calculator")
@Slf4j
public class CalculatorController {

    private TaxCalculatorService taxCalculatorService;

    private FillingParametersService fillingParametersService;

    public CalculatorController(TaxCalculatorService taxCalculatorService, FillingParametersService fillingParametersService) {
        this.taxCalculatorService = taxCalculatorService;
        this.fillingParametersService = fillingParametersService;
    }

    @PostMapping
    public String calculate(@ModelAttribute("fillingParametersDTO") FillingParametersDTO fillingParametersDTO,
                            BindingResult bindingResult, Model model) {
        log.info("Filling parameters = {}", fillingParametersDTO);

        if (fillingParametersDTO.getYear().equals("Select Year")) {
            bindingResult.rejectValue("year", null, "Please select year");
        }

        if (fillingParametersDTO.getState().equals("Select State")) {
            bindingResult.rejectValue("state", null, "Please select state");
        }

        if (fillingParametersDTO.getFillingStatus().equals("Select Filling Status")) {
            bindingResult.rejectValue("fillingStatus", null, "Please select filling status");
        }

        if (fillingParametersDTO.getIncome() == null || fillingParametersDTO.getIncome().equals("")) {
            bindingResult.rejectValue("income", null, "Income should not be empty");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("fillingParametersDTO", fillingParametersDTO);
            model.addAttribute("listYear", AppConstants.YEAR_LIST);
            model.addAttribute("listState", AppConstants.STATE_LIST);
            model.addAttribute("listFillingStatus", AppConstants.FILLING_STATUS_LIST);
            return "home";
        }

        Double stateTaxAmount = null;
        try {
            stateTaxAmount = taxCalculatorService.calculateStateTax(Integer.parseInt(fillingParametersDTO.getYear()),
                    fillingParametersDTO.getState(), fillingParametersDTO.getFillingStatus(), Integer.parseInt(fillingParametersDTO.getIncome()));
        } catch (IncomeTaxCalculatorException e) {
            bindingResult.rejectValue("income", null, e.getMessage());
        }
        log.info("State tax amount = {}", stateTaxAmount);
        fillingParametersDTO.setStateTaxAmount(stateTaxAmount);

        if (bindingResult.hasErrors()) {
            model.addAttribute("fillingParametersDTO", fillingParametersDTO);
            model.addAttribute("listYear", AppConstants.YEAR_LIST);
            model.addAttribute("listState", AppConstants.STATE_LIST);
            model.addAttribute("listFillingStatus", AppConstants.FILLING_STATUS_LIST);
            return "redirect:/home";
        }

        Double federalTaxAmount = null;
        try {
            federalTaxAmount = taxCalculatorService.calculateFederalTax(Integer.parseInt(fillingParametersDTO.getYear()),
                    fillingParametersDTO.getFillingStatus(), Integer.parseInt(fillingParametersDTO.getIncome()));
        } catch (IncomeTaxCalculatorException e) {
            bindingResult.rejectValue("income", null, e.getMessage());
        }
        log.info("Federal tax amount = {}", federalTaxAmount);
        fillingParametersDTO.setFederalTaxAmount(federalTaxAmount);

        if (bindingResult.hasErrors()) {
            model.addAttribute("fillingParametersDTO", fillingParametersDTO);
            model.addAttribute("listYear", AppConstants.YEAR_LIST);
            model.addAttribute("listState", AppConstants.STATE_LIST);
            model.addAttribute("listFillingStatus", AppConstants.FILLING_STATUS_LIST);
            return "redirect:/home";
        }

        Double ficaTaxAmount = taxCalculatorService.calculateFicaTax(Integer.parseInt(fillingParametersDTO.getYear()),
                Integer.parseInt(fillingParametersDTO.getIncome()));
        log.info("FICA tax amount = {}", ficaTaxAmount);
        fillingParametersDTO.setFicaTaxAmount(ficaTaxAmount);

        Double additionalMedicareTaxAmount = taxCalculatorService.calculateAdditionalMedicareTax(Integer.parseInt(fillingParametersDTO.getYear()),
                fillingParametersDTO.getFillingStatus(), Integer.parseInt(fillingParametersDTO.getIncome()));
        log.info("Additional medicare tax amount = {}", additionalMedicareTaxAmount);
        fillingParametersDTO.setAdditionalMedicareTaxAmount(additionalMedicareTaxAmount);

        Double totalTaxAmount = stateTaxAmount + federalTaxAmount + ficaTaxAmount + additionalMedicareTaxAmount;
        log.info("Total tax amount = {}", totalTaxAmount);
        fillingParametersDTO.setTotalTaxAmount(totalTaxAmount);

        Double effectiveTaxRate = taxCalculatorService.calculateEffectiveTaxRate(totalTaxAmount, Integer.parseInt(fillingParametersDTO.getIncome()));
        log.info("Effective tax rate = {}", effectiveTaxRate);
        fillingParametersDTO.setEffectiveTaxRate(effectiveTaxRate);

        fillingParametersService.save(fillingParametersDTO);

        model.addAttribute("fillingParametersDTO", fillingParametersDTO);
        model.addAttribute("listYear", AppConstants.YEAR_LIST);
        model.addAttribute("listState", AppConstants.STATE_LIST);
        model.addAttribute("listFillingStatus", AppConstants.FILLING_STATUS_LIST);

        return "results";
    }
}
