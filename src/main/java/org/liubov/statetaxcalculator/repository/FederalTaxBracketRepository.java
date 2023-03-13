package org.liubov.statetaxcalculator.repository;

import org.liubov.statetaxcalculator.model.FederalTaxBracket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FederalTaxBracketRepository extends JpaRepository<FederalTaxBracket, Integer> {

    List<FederalTaxBracket> findByYearAndFillingStatusOrderByBracketLowerDesc(Integer year, String fillingStatus);
}
