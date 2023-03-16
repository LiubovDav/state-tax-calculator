package org.liubov.statetaxcalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.config.AppConstants;
import org.liubov.statetaxcalculator.dto.FilingParametersDTO;
import org.liubov.statetaxcalculator.exception.IncomeTaxCalculatorException;
import org.liubov.statetaxcalculator.service.FilingParametersService;
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

    private FilingParametersService filingParametersService;

    public CalculatorController(TaxCalculatorService taxCalculatorService, FilingParametersService filingParametersService) {
        this.taxCalculatorService = taxCalculatorService;
        this.filingParametersService = filingParametersService;
    }

    @PostMapping
    public String calculate(@ModelAttribute("filingParametersDTO") FilingParametersDTO filingParametersDTO,
                            BindingResult bindingResult, Model model) {
        log.info("Filing parameters = {}", filingParametersDTO);

        if (filingParametersDTO.getYear().equals("Select Year")) {
            bindingResult.rejectValue("year", null, "Please select year");
        }

        if (filingParametersDTO.getState().equals("Select State")) {
            bindingResult.rejectValue("state", null, "Please select state");
        }

        if (filingParametersDTO.getFilingStatus().equals("Select Filing Status")) {
            bindingResult.rejectValue("filingStatus", null, "Please select filing status");
        }

        if (filingParametersDTO.getIncome() == null || filingParametersDTO.getIncome().equals("")) {
            bindingResult.rejectValue("income", null, "Income should not be empty");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("filingParametersDTO", filingParametersDTO);
            model.addAttribute("listYear", AppConstants.YEAR_LIST);
            model.addAttribute("listState", AppConstants.STATE_LIST);
            model.addAttribute("listFilingStatus", AppConstants.FILING_STATUS_LIST);
            return "home";
        }

        Double stateTaxAmount = null;
        try {
            stateTaxAmount = taxCalculatorService.calculateStateTax(Integer.parseInt(filingParametersDTO.getYear()),
                    filingParametersDTO.getState(), filingParametersDTO.getFilingStatus(), filingParametersDTO.getIncome());
        } catch (IncomeTaxCalculatorException e) {
            bindingResult.rejectValue("income", null, e.getMessage());
        }
        log.info("State tax amount = {}", stateTaxAmount);
        filingParametersDTO.setStateTaxAmount(stateTaxAmount);

        if (bindingResult.hasErrors()) {
            model.addAttribute("filingParametersDTO", filingParametersDTO);
            model.addAttribute("listYear", AppConstants.YEAR_LIST);
            model.addAttribute("listState", AppConstants.STATE_LIST);
            model.addAttribute("listFilingStatus", AppConstants.FILING_STATUS_LIST);
            return "redirect:/home";
        }

        Double federalTaxAmount = null;
        try {
            federalTaxAmount = taxCalculatorService.calculateFederalTax(Integer.parseInt(filingParametersDTO.getYear()),
                    filingParametersDTO.getFilingStatus(), filingParametersDTO.getIncome());
        } catch (IncomeTaxCalculatorException e) {
            bindingResult.rejectValue("income", null, e.getMessage());
        }
        log.info("Federal tax amount = {}", federalTaxAmount);
        filingParametersDTO.setFederalTaxAmount(federalTaxAmount);

        if (bindingResult.hasErrors()) {
            model.addAttribute("filingParametersDTO", filingParametersDTO);
            model.addAttribute("listYear", AppConstants.YEAR_LIST);
            model.addAttribute("listState", AppConstants.STATE_LIST);
            model.addAttribute("listFilingStatus", AppConstants.FILING_STATUS_LIST);
            return "redirect:/home";
        }

        Double ficaTaxAmount = taxCalculatorService.calculateFicaTax(Integer.parseInt(filingParametersDTO.getYear()),
                filingParametersDTO.getIncome());
        log.info("FICA tax amount = {}", ficaTaxAmount);
        filingParametersDTO.setFicaTaxAmount(ficaTaxAmount);

        Double additionalMedicareTaxAmount = taxCalculatorService.calculateAdditionalMedicareTax(Integer.parseInt(filingParametersDTO.getYear()),
                filingParametersDTO.getFilingStatus(), filingParametersDTO.getIncome());
        log.info("Additional medicare tax amount = {}", additionalMedicareTaxAmount);
        filingParametersDTO.setAdditionalMedicareTaxAmount(additionalMedicareTaxAmount);

        Double totalTaxAmount = Math.round((stateTaxAmount + federalTaxAmount + ficaTaxAmount + additionalMedicareTaxAmount) * 100.0) / 100.0;
        log.info("Total tax amount = {}", totalTaxAmount);
        filingParametersDTO.setTotalTaxAmount(totalTaxAmount);

        Double effectiveTaxRate = taxCalculatorService.calculateEffectiveTaxRate(totalTaxAmount, filingParametersDTO.getIncome());
        log.info("Effective tax rate = {}", effectiveTaxRate);
        filingParametersDTO.setEffectiveTaxRate(effectiveTaxRate);

        Double afterTaxAmount = filingParametersDTO.getIncome() - totalTaxAmount;
        log.info("After tax amount = {}", afterTaxAmount);
        filingParametersDTO.setAfterTaxAmount(afterTaxAmount);

        filingParametersService.save(filingParametersDTO);

        model.addAttribute("filingParametersDTO", filingParametersDTO);
        model.addAttribute("listYear", AppConstants.YEAR_LIST);
        model.addAttribute("listState", AppConstants.STATE_LIST);
        model.addAttribute("listFilingStatus", AppConstants.FILING_STATUS_LIST);

        return "results";
    }
}
