package org.liubov.statetaxcalculator.mapper;

import org.liubov.statetaxcalculator.dto.FillingParametersDTO;
import org.liubov.statetaxcalculator.model.FillingParameters;
import org.springframework.stereotype.Component;

@Component
public class FillingParametersMapper {

    public FillingParameters convertToFillingParameters(FillingParametersDTO fillingParametersDTO) {
        FillingParameters fillingParameters = new FillingParameters();
        // todo: uncomment
//        fillingParameters.setUserId(fillingParametersDTO.getUserId());
        fillingParameters.setUserId(1);
        fillingParameters.setYear(Integer.parseInt(fillingParametersDTO.getYear()));
        fillingParameters.setState(fillingParametersDTO.getState());
        fillingParameters.setFillingStatus(fillingParametersDTO.getFillingStatus());
        fillingParameters.setIncome(Integer.parseInt(fillingParametersDTO.getIncome()));
        fillingParameters.setStateTaxAmount(fillingParametersDTO.getStateTaxAmount());
        fillingParameters.setFederalTaxAmount(fillingParametersDTO.getFederalTaxAmount());
        fillingParameters.setFicaTaxAmount(fillingParametersDTO.getFicaTaxAmount());
        fillingParameters.setAdditionalMedicareTaxAmount(fillingParametersDTO.getAdditionalMedicareTaxAmount());
        fillingParameters.setTotalTaxAmount(fillingParametersDTO.getTotalTaxAmount());
        fillingParameters.setEffectiveTaxRate(fillingParametersDTO.getEffectiveTaxRate());
        fillingParameters.setCreatedOn(fillingParametersDTO.getCreatedOn());
        fillingParameters.setUpdatedOn(fillingParametersDTO.getUpdatedOn());

        return fillingParameters;
    }

    public FillingParametersDTO convertToFillingParametersDTO(FillingParameters fillingParameters) {
        FillingParametersDTO fillingParametersDTO = new FillingParametersDTO();
        fillingParametersDTO.setId(fillingParameters.getId());
        fillingParametersDTO.setUserId(fillingParameters.getUserId());
        fillingParametersDTO.setYear("" + fillingParameters.getYear());
        fillingParametersDTO.setState(fillingParameters.getState());
        fillingParametersDTO.setFillingStatus(fillingParameters.getFillingStatus());
        fillingParametersDTO.setIncome("" + fillingParameters.getIncome());
        fillingParametersDTO.setStateTaxAmount(fillingParameters.getStateTaxAmount());
        fillingParametersDTO.setFederalTaxAmount(fillingParameters.getFederalTaxAmount());
        fillingParametersDTO.setFicaTaxAmount(fillingParameters.getFicaTaxAmount());
        fillingParametersDTO.setAdditionalMedicareTaxAmount(fillingParameters.getAdditionalMedicareTaxAmount());
        fillingParametersDTO.setTotalTaxAmount(fillingParameters.getTotalTaxAmount());
        fillingParametersDTO.setEffectiveTaxRate(fillingParameters.getEffectiveTaxRate());
        fillingParametersDTO.setCreatedOn(fillingParameters.getCreatedOn());
        fillingParametersDTO.setUpdatedOn(fillingParameters.getUpdatedOn());

        return fillingParametersDTO;
    }
}
