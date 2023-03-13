package org.liubov.statetaxcalculator.controller.api;

import org.liubov.statetaxcalculator.model.FederalTaxBracket;
import org.liubov.statetaxcalculator.service.FederalTaxBracketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/federal_tax_bracket")
public class FederalTaxBracketController {

    private FederalTaxBracketService federalTaxBracketService;

    public FederalTaxBracketController(FederalTaxBracketService federalTaxBracketService) {
        this.federalTaxBracketService = federalTaxBracketService;
    }

    @GetMapping("/find_test")
    public List<FederalTaxBracket> findByYearAndFillingStatus() {
       return federalTaxBracketService.findByYearAndFillingStatus("2022", "Single");
    }
}
