package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.model.TaxRate;

import java.util.List;

public interface TaxRateService {

    List<TaxRate> findByStateAndFillingStatus(String state, String fillingStatus);
}
