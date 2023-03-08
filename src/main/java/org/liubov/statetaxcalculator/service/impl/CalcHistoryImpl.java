package org.liubov.statetaxcalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.model.CalcHistory;
import org.liubov.statetaxcalculator.repository.CalcHistoryRepository;
import org.liubov.statetaxcalculator.service.CalcHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CalcHistoryImpl implements CalcHistoryService {

    @Autowired
    private CalcHistoryRepository calcHistoryRepository;

    @Override
    public void save(CalcHistory calcHistory) {

        calcHistoryRepository.save(calcHistory);
        log.info("CalcHistory was successfully saved");
    }

    @Override
    public List<CalcHistory> findByUserId(Integer userId) {
        return calcHistoryRepository.findByUserId(userId);
    }
}
