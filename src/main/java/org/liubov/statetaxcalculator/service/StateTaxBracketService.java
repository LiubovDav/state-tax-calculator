package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.model.StateTaxBracket;

import java.util.List;

public interface StateTaxBracketService {

    List<StateTaxBracket> findByYearAndStateAndFillingStatus(String year, String state, String fillingStatus);
}
