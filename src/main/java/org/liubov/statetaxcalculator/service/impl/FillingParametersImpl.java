package org.liubov.statetaxcalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.dto.FillingParametersDTO;
import org.liubov.statetaxcalculator.mapper.FillingParametersMapper;
import org.liubov.statetaxcalculator.model.FillingParameters;
import org.liubov.statetaxcalculator.repository.FillingParametersRepository;
import org.liubov.statetaxcalculator.service.FillingParametersService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FillingParametersImpl implements FillingParametersService {

    private FillingParametersRepository fillingParametersRepository;

    private FillingParametersMapper fillingParametersMapper;

    public FillingParametersImpl(FillingParametersRepository fillingParametersRepository, FillingParametersMapper fillingParametersMapper) {
        this.fillingParametersRepository = fillingParametersRepository;
        this.fillingParametersMapper = fillingParametersMapper;
    }

    @Override
    public void save(FillingParametersDTO fillingParametersDTO) {
        fillingParametersRepository.save(fillingParametersMapper.convertToFillingParameters(fillingParametersDTO));
        log.info("FillingParameters was successfully saved");
    }

    @Override
    public List<FillingParametersDTO> findByUserId(Integer userId) {
        List<FillingParametersDTO> fillingParametersDTOList = new ArrayList<>();

        List<FillingParameters> fillingParametersList = fillingParametersRepository.findByUserId(userId);
        for (FillingParameters fillingParameters : fillingParametersList) {
            fillingParametersDTOList.add(fillingParametersMapper.convertToFillingParametersDTO(fillingParameters));
        }

        return fillingParametersDTOList;
    }
}
