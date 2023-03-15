package org.liubov.statetaxcalculator.mapper;

import org.liubov.statetaxcalculator.dto.FilingParametersDTO;
import org.liubov.statetaxcalculator.model.FilingParameters;
import org.springframework.stereotype.Component;

@Component
public class FilingParametersMapper {

    public FilingParameters convertToFilingParameters(FilingParametersDTO filingParametersDTO) {
        FilingParameters filingParameters = new FilingParameters();
        // todo: uncomment
//        filingParameters.setUserId(filingParametersDTO.getUserId());
        filingParameters.setUserId(1);
        filingParameters.setYear(Integer.parseInt(filingParametersDTO.getYear()));
        filingParameters.setState(filingParametersDTO.getState());
        filingParameters.setFilingStatus(filingParametersDTO.getFilingStatus());
        filingParameters.setIncome(Integer.parseInt(filingParametersDTO.getIncome()));
        filingParameters.setStateTaxAmount(filingParametersDTO.getStateTaxAmount());
        filingParameters.setFederalTaxAmount(filingParametersDTO.getFederalTaxAmount());
        filingParameters.setFicaTaxAmount(filingParametersDTO.getFicaTaxAmount());
        filingParameters.setAdditionalMedicareTaxAmount(filingParametersDTO.getAdditionalMedicareTaxAmount());
        filingParameters.setTotalTaxAmount(filingParametersDTO.getTotalTaxAmount());
        filingParameters.setEffectiveTaxRate(filingParametersDTO.getEffectiveTaxRate());
        filingParameters.setCreatedOn(filingParametersDTO.getCreatedOn());
        filingParameters.setUpdatedOn(filingParametersDTO.getUpdatedOn());

        return filingParameters;
    }

    public FilingParametersDTO convertToFilingParametersDTO(FilingParameters filingParameters) {
        FilingParametersDTO filingParametersDTO = new FilingParametersDTO();
        filingParametersDTO.setId(filingParameters.getId());
        filingParametersDTO.setUserId(filingParameters.getUserId());
        filingParametersDTO.setYear("" + filingParameters.getYear());
        filingParametersDTO.setState(filingParameters.getState());
        filingParametersDTO.setFilingStatus(filingParameters.getFilingStatus());
        filingParametersDTO.setIncome("" + filingParameters.getIncome());
        filingParametersDTO.setStateTaxAmount(filingParameters.getStateTaxAmount());
        filingParametersDTO.setFederalTaxAmount(filingParameters.getFederalTaxAmount());
        filingParametersDTO.setFicaTaxAmount(filingParameters.getFicaTaxAmount());
        filingParametersDTO.setAdditionalMedicareTaxAmount(filingParameters.getAdditionalMedicareTaxAmount());
        filingParametersDTO.setTotalTaxAmount(filingParameters.getTotalTaxAmount());
        filingParametersDTO.setEffectiveTaxRate(filingParameters.getEffectiveTaxRate());
        filingParametersDTO.setCreatedOn(filingParameters.getCreatedOn());
        filingParametersDTO.setUpdatedOn(filingParameters.getUpdatedOn());

        return filingParametersDTO;
    }
}
