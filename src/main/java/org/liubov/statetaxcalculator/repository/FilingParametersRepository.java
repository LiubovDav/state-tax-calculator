package org.liubov.statetaxcalculator.repository;

import org.liubov.statetaxcalculator.model.FilingParameters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilingParametersRepository extends JpaRepository<FilingParameters, Integer> {

    List<FilingParameters> findByUserId(Integer userId);
}
