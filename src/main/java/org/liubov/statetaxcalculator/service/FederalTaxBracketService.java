package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.model.FederalTaxBracket;

import java.util.List;

public interface FederalTaxBracketService {

    List<FederalTaxBracket> findByYearAndFillingStatus(String year, String fillingStatus);
}
