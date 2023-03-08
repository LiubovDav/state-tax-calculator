package org.liubov.statetaxcalculator.controller;

import org.liubov.statetaxcalculator.model.CalcHistory;
import org.liubov.statetaxcalculator.service.CalcHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/calc_history")
public class CalcHistoryController {

    @Autowired
    private CalcHistoryService calcHistoryService;

    @GetMapping("/save")
    public void saveCalcHistory() {
        CalcHistory calcHistory = new CalcHistory();
        calcHistory.setUserId(1);
        calcHistory.setYear(2022);
        calcHistory.setState("CA");
        calcHistory.setFillingStatus("Single");
        calcHistory.setIncome(150_000);
        calcHistory.setStateTaxAmount(16_500.50);
        calcHistory.setFederalTaxAmount(20_000.53);
        calcHistoryService.save(calcHistory);
    }
    @GetMapping("/find_by_user_id")
    public List<CalcHistory> findByUserId() {
        return calcHistoryService.findByUserId(1);
    }


}
