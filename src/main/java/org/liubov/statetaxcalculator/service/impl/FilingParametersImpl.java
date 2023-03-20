package org.liubov.statetaxcalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.dto.FilingParametersDTO;
import org.liubov.statetaxcalculator.mapper.FilingParametersMapper;
import org.liubov.statetaxcalculator.model.FilingParameters;
import org.liubov.statetaxcalculator.repository.FilingParametersRepository;
import org.liubov.statetaxcalculator.service.FilingParametersService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FilingParametersImpl implements FilingParametersService {

    private FilingParametersRepository filingParametersRepository;

    private FilingParametersMapper filingParametersMapper;

    public FilingParametersImpl(FilingParametersRepository filingParametersRepository, FilingParametersMapper filingParametersMapper) {
        this.filingParametersRepository = filingParametersRepository;
        this.filingParametersMapper = filingParametersMapper;
    }

    @Override
    public FilingParametersDTO save(FilingParametersDTO filingParametersDTO) throws ParseException {
        FilingParameters filingParameters = filingParametersRepository.save(filingParametersMapper.toFilingParameters(filingParametersDTO));
        log.info("FilingParameters was successfully saved");
        return filingParametersMapper.toFilingParametersDTO(filingParameters);
    }

    @Override
    public List<FilingParametersDTO> findByUserId(Integer userId) {
        List<FilingParametersDTO> filingParametersDTOList = new ArrayList<>();

        List<FilingParameters> filingParametersList = filingParametersRepository.findByUserId(userId);
        for (FilingParameters filingParameters : filingParametersList) {
            filingParametersDTOList.add(filingParametersMapper.toFilingParametersDTO(filingParameters));
        }

        return filingParametersDTOList;
    }
}
