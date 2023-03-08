package org.liubov.statetaxcalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.model.TaxRate;
import org.liubov.statetaxcalculator.repository.TaxRateRepository;
import org.liubov.statetaxcalculator.service.TaxRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaxRateServiceImpl implements TaxRateService {

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Override
    public List<TaxRate> findByStateAndFillingStatus(String state, String fillingStatus) {
        return taxRateRepository.findByStateAndFillingStatus(state, fillingStatus);
    }
}
