package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.dto.FillingParametersDTO;

import java.util.List;

public interface FillingParametersService {

    void save(FillingParametersDTO fillingParametersDTO);

    List<FillingParametersDTO> findByUserId(Integer userId);
}
