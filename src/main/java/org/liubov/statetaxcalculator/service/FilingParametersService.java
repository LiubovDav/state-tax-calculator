package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.dto.FilingParametersDTO;

import java.text.ParseException;
import java.util.List;

public interface FilingParametersService {

    FilingParametersDTO save(FilingParametersDTO filingParametersDTO) throws ParseException;

    List<FilingParametersDTO> findByUserId(Integer userId);
}
