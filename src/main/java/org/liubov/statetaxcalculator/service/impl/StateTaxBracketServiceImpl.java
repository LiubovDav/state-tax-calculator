package org.liubov.statetaxcalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.model.StateTaxBracket;
import org.liubov.statetaxcalculator.repository.StateTaxBracketRepository;
import org.liubov.statetaxcalculator.service.StateTaxBracketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StateTaxBracketServiceImpl implements StateTaxBracketService {

    private StateTaxBracketRepository stateTaxBracketRepository;

    public StateTaxBracketServiceImpl(StateTaxBracketRepository stateTaxBracketRepository) {
        this.stateTaxBracketRepository = stateTaxBracketRepository;
    }

    @Override
    public List<StateTaxBracket> findByYearAndStateAndFilingStatus(String year, String state, String filingStatus) {
        return stateTaxBracketRepository.findByYearAndStateAndFilingStatusOrderByBracketLowerDesc(Integer.parseInt(year), state, filingStatus);
    }
}
