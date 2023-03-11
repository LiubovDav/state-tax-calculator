package org.liubov.statetaxcalculator.repository;

import org.liubov.statetaxcalculator.model.TaxRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxRateRepository extends JpaRepository<TaxRate, Integer> {

    List<TaxRate> findByStateAndFillingStatus(String state, String fillingStatus);
}
