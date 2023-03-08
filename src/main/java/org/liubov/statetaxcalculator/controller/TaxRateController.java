package org.liubov.statetaxcalculator.controller;

import org.liubov.statetaxcalculator.model.TaxRate;
import org.liubov.statetaxcalculator.service.TaxRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/tax_rate")
public class TaxRateController {

    @Autowired
    private TaxRateService taxRateService;

    @GetMapping
    public List<TaxRate> getRates() {
       return taxRateService.findByStateAndFillingStatus("CA", "Single");
    }
}
