package org.liubov.statetaxcalculator.controller.api;

import org.liubov.statetaxcalculator.model.StateTaxBracket;
import org.liubov.statetaxcalculator.service.StateTaxBracketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/state_tax_bracket")
public class StateTaxBracketController {

    private StateTaxBracketService stateTaxBracketService;

    public StateTaxBracketController(StateTaxBracketService stateTaxBracketService) {
        this.stateTaxBracketService = stateTaxBracketService;
    }

    @GetMapping("/find_test")
    public List<StateTaxBracket> findByYearAndStateAndFilingStatus() {
       return stateTaxBracketService.findByYearAndStateAndFilingStatus("2022", "CA", "Single");
    }
}
