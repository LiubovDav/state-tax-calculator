package org.liubov.statetaxcalculator.repository;

import org.liubov.statetaxcalculator.model.TaxRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRateRepository extends CrudRepository<TaxRate, Integer> {

    List<TaxRate> findByStateAndFillingStatus(String state, String fillingStatus);
}
