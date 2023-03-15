package org.liubov.statetaxcalculator.repository;

import org.liubov.statetaxcalculator.model.StateTaxBracket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateTaxBracketRepository extends JpaRepository<StateTaxBracket, Integer> {

    List<StateTaxBracket> findByYearAndStateAndFilingStatusOrderByBracketLowerDesc(Integer year, String state, String filingStatus);
}
