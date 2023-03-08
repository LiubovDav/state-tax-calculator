package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.model.CalcHistory;

import java.util.List;

public interface CalcHistoryService {

    void save(CalcHistory calcHistory);

    List<CalcHistory> findByUserId(Integer userId);
}
