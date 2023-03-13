package org.liubov.statetaxcalculator.repository;

import org.liubov.statetaxcalculator.model.FillingParameters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FillingParametersRepository extends JpaRepository<FillingParameters, Integer> {

    List<FillingParameters> findByUserId(Integer userId);
}
