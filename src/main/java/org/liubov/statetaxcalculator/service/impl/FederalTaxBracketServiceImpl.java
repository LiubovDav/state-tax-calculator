package org.liubov.statetaxcalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.model.FederalTaxBracket;
import org.liubov.statetaxcalculator.repository.FederalTaxBracketRepository;
import org.liubov.statetaxcalculator.service.FederalTaxBracketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FederalTaxBracketServiceImpl implements FederalTaxBracketService {

    private FederalTaxBracketRepository federalTaxBracketRepository;

    public FederalTaxBracketServiceImpl(FederalTaxBracketRepository federalTaxBracketRepository) {
        this.federalTaxBracketRepository = federalTaxBracketRepository;
    }

    @Override
    public List<FederalTaxBracket> findByYearAndFilingStatus(String year, String filingStatus) {
        return federalTaxBracketRepository.findByYearAndFilingStatusOrderByBracketLowerDesc(Integer.parseInt(year), filingStatus);
    }
}
