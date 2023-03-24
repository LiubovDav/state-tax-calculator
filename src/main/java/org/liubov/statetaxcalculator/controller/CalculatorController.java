package org.liubov.statetaxcalculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.config.AppConstants;
import org.liubov.statetaxcalculator.dto.FilingParametersDTO;
import org.liubov.statetaxcalculator.exception.IncomeTaxCalculatorException;
import org.liubov.statetaxcalculator.service.FilingParametersService;
import org.liubov.statetaxcalculator.service.TaxCalculatorService;
import org.liubov.statetaxcalculator.util.DecimalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;

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
                            BindingResult bindingResult, Model model) throws ParseException {
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

        BigDecimal stateTaxAmount = null;
        try {
            stateTaxAmount = taxCalculatorService.calculateStateTax(Integer.parseInt(filingParametersDTO.getYear()),
                    filingParametersDTO.getState(), filingParametersDTO.getFilingStatus(), new BigDecimal(filingParametersDTO.getIncome()));
        } catch (IncomeTaxCalculatorException e) {
            bindingResult.rejectValue("income", null, e.getMessage());
        }
        log.info("State tax amount = {}", stateTaxAmount);
        filingParametersDTO.setStateTaxAmount(DecimalUtil.toString(stateTaxAmount));

        if (bindingResult.hasErrors()) {
            model.addAttribute("filingParametersDTO", filingParametersDTO);
            model.addAttribute("listYear", AppConstants.YEAR_LIST);
            model.addAttribute("listState", AppConstants.STATE_LIST);
            model.addAttribute("listFilingStatus", AppConstants.FILING_STATUS_LIST);
            return "redirect:/home";
        }

        BigDecimal federalTaxAmount = null;
        try {
            federalTaxAmount = taxCalculatorService.calculateFederalTax(Integer.parseInt(filingParametersDTO.getYear()),
                    filingParametersDTO.getFilingStatus(), new BigDecimal(filingParametersDTO.getIncome()));
        } catch (IncomeTaxCalculatorException e) {
            bindingResult.rejectValue("income", null, e.getMessage());
        }
        log.info("Federal tax amount = {}", federalTaxAmount);
        filingParametersDTO.setFederalTaxAmount(DecimalUtil.toString(federalTaxAmount));

        if (bindingResult.hasErrors()) {
            model.addAttribute("filingParametersDTO", filingParametersDTO);
            model.addAttribute("listYear", AppConstants.YEAR_LIST);
            model.addAttribute("listState", AppConstants.STATE_LIST);
            model.addAttribute("listFilingStatus", AppConstants.FILING_STATUS_LIST);
            return "redirect:/home";
        }

        BigDecimal socialSecurityTaxAmount = taxCalculatorService.calculateSocialSecurityTax(Integer.parseInt(filingParametersDTO.getYear()),
                new BigDecimal(filingParametersDTO.getIncome()));
        log.info("Social Security tax amount = {}", socialSecurityTaxAmount);
        filingParametersDTO.setSocialSecurityTaxAmount(DecimalUtil.toString(socialSecurityTaxAmount));

        BigDecimal medicareTaxAmount = taxCalculatorService.calculateMedicareTax(Integer.parseInt(filingParametersDTO.getYear()),
                new BigDecimal(filingParametersDTO.getIncome()));
        log.info("Medicare tax amount = {}", medicareTaxAmount);
        filingParametersDTO.setMedicareTaxAmount(DecimalUtil.toString(medicareTaxAmount));

        BigDecimal additionalMedicareTaxAmount = taxCalculatorService.calculateAdditionalMedicareTax(Integer.parseInt(filingParametersDTO.getYear()),
                filingParametersDTO.getFilingStatus(), new BigDecimal(filingParametersDTO.getIncome()));
        log.info("Additional medicare tax amount = {}", additionalMedicareTaxAmount);
        filingParametersDTO.setAdditionalMedicareTaxAmount(DecimalUtil.toString(additionalMedicareTaxAmount));

        BigDecimal totalTaxAmount = stateTaxAmount.add(federalTaxAmount).add(medicareTaxAmount).add(additionalMedicareTaxAmount);
        log.info("Total tax amount = {}", totalTaxAmount);
        filingParametersDTO.setTotalTaxAmount(DecimalUtil.toString(totalTaxAmount));

        BigDecimal effectiveTaxRate = taxCalculatorService.calculateEffectiveTaxRate(totalTaxAmount, new BigDecimal(filingParametersDTO.getIncome()));
        log.info("Effective tax rate = {}", effectiveTaxRate);
        filingParametersDTO.setEffectiveTaxRate(DecimalUtil.toString(effectiveTaxRate));

        BigDecimal afterTaxAmount = new BigDecimal(filingParametersDTO.getIncome()).subtract(totalTaxAmount);
        log.info("After tax amount = {}", afterTaxAmount);
        filingParametersDTO.setAfterTaxAmount(DecimalUtil.toString(afterTaxAmount));

        filingParametersDTO = filingParametersService.save(filingParametersDTO);

        model.addAttribute("filingParametersDTO", filingParametersDTO);
        model.addAttribute("listYear", AppConstants.YEAR_LIST);
        model.addAttribute("listState", AppConstants.STATE_LIST);
        model.addAttribute("listFilingStatus", AppConstants.FILING_STATUS_LIST);

        return "results";
    }
}
