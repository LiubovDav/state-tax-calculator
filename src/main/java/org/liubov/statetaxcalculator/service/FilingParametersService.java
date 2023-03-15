package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.dto.FilingParametersDTO;

import java.util.List;

public interface FilingParametersService {

    void save(FilingParametersDTO filingParametersDTO);

    List<FilingParametersDTO> findByUserId(Integer userId);
}
