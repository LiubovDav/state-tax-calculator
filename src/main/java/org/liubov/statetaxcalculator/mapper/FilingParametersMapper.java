package org.liubov.statetaxcalculator.mapper;

import org.liubov.statetaxcalculator.dto.FilingParametersDTO;
import org.liubov.statetaxcalculator.model.FilingParameters;
import org.liubov.statetaxcalculator.util.DecimalUtil;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class FilingParametersMapper {

    public FilingParameters toFilingParameters(FilingParametersDTO filingParametersDTO) throws ParseException {
        FilingParameters filingParameters = new FilingParameters();
        // todo: uncomment
//        filingParameters.setUserId(filingParametersDTO.getUserId());
        filingParameters.setUserId(1);
        filingParameters.setYear(Integer.parseInt(filingParametersDTO.getYear()));
        filingParameters.setState(filingParametersDTO.getState());
        filingParameters.setFilingStatus(filingParametersDTO.getFilingStatus());
        filingParameters.setIncome(DecimalUtil.toBigDecimal(filingParametersDTO.getIncome()));
        filingParameters.setStateTaxAmount(DecimalUtil.toBigDecimal(filingParametersDTO.getStateTaxAmount()));
        filingParameters.setFederalTaxAmount(DecimalUtil.toBigDecimal(filingParametersDTO.getFederalTaxAmount()));
        filingParameters.setFicaTaxAmount(DecimalUtil.toBigDecimal(filingParametersDTO.getFicaTaxAmount()));
        filingParameters.setAdditionalMedicareTaxAmount(DecimalUtil.toBigDecimal(filingParametersDTO.getAdditionalMedicareTaxAmount()));
        filingParameters.setTotalTaxAmount(DecimalUtil.toBigDecimal(filingParametersDTO.getTotalTaxAmount()));
        filingParameters.setEffectiveTaxRate(DecimalUtil.toBigDecimal(filingParametersDTO.getEffectiveTaxRate()));
        filingParameters.setAfterTaxAmount(DecimalUtil.toBigDecimal(filingParametersDTO.getAfterTaxAmount()));
        filingParameters.setCreatedOn(filingParametersDTO.getCreatedOn());
        filingParameters.setUpdatedOn(filingParametersDTO.getUpdatedOn());

        return filingParameters;
    }

    public FilingParametersDTO toFilingParametersDTO(FilingParameters filingParameters) {
        FilingParametersDTO filingParametersDTO = new FilingParametersDTO();
        filingParametersDTO.setId(filingParameters.getId());
        filingParametersDTO.setUserId(filingParameters.getUserId());
        filingParametersDTO.setYear("" + filingParameters.getYear());
        filingParametersDTO.setState(filingParameters.getState());
        filingParametersDTO.setFilingStatus(filingParameters.getFilingStatus());
        filingParametersDTO.setIncome(DecimalUtil.toString(filingParameters.getIncome()));
        filingParametersDTO.setStateTaxAmount(DecimalUtil.toString(filingParameters.getStateTaxAmount()));
        filingParametersDTO.setFederalTaxAmount(DecimalUtil.toString(filingParameters.getFederalTaxAmount()));
        filingParametersDTO.setFicaTaxAmount(DecimalUtil.toString(filingParameters.getFicaTaxAmount()));
        filingParametersDTO.setAdditionalMedicareTaxAmount(DecimalUtil.toString(filingParameters.getAdditionalMedicareTaxAmount()));
        filingParametersDTO.setTotalTaxAmount(DecimalUtil.toString(filingParameters.getTotalTaxAmount()));
        filingParametersDTO.setEffectiveTaxRate(DecimalUtil.toString(filingParameters.getEffectiveTaxRate()));
        filingParametersDTO.setAfterTaxAmount(DecimalUtil.toString(filingParameters.getAfterTaxAmount()));
        filingParametersDTO.setCreatedOn(filingParameters.getCreatedOn());
        filingParametersDTO.setUpdatedOn(filingParameters.getUpdatedOn());

        return filingParametersDTO;
    }
}
