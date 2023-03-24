package org.liubov.statetaxcalculator.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.liubov.statetaxcalculator.model.FilingParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
class FilingParametersRepositoryTests {
    @Autowired
    private FilingParametersRepository filingParametersRepository;

    @DisplayName("JUnit test for find FilingParameters list by user id operation")
    @Test
    void givenFilingParametersList_whenFindByUserId_thenReturnFilingParametersList() {
        // given - precondition or setup
        FilingParameters filingParameters = new FilingParameters();
        filingParameters.setUserId(15);
        filingParameters.setYear(2022);
        filingParameters.setState("CA");
        filingParameters.setFilingStatus("Single");
        filingParameters.setIncome(new BigDecimal(135_000.0));
        filingParameters.setStateTaxAmount(new BigDecimal(7_000.45));
        filingParameters.setFederalTaxAmount(new BigDecimal(10_345.43));

        FilingParameters filingParameters2 = new FilingParameters();
        filingParameters2.setUserId(34);
        filingParameters2.setYear(2022);
        filingParameters2.setState("NY");
        filingParameters2.setFilingStatus("Single");
        filingParameters2.setIncome(new BigDecimal(100_000.0));
        filingParameters2.setStateTaxAmount(new BigDecimal(6_567.67));
        filingParameters2.setFederalTaxAmount(new BigDecimal(8_456.89));

        FilingParameters filingParameters3 = new FilingParameters();
        filingParameters3.setUserId(15);
        filingParameters3.setYear(2021);
        filingParameters3.setState("CA");
        filingParameters3.setFilingStatus("Single");
        filingParameters3.setIncome(new BigDecimal(124_000.0));
        filingParameters3.setStateTaxAmount(new BigDecimal(6_098.34));
        filingParameters3.setFederalTaxAmount(new BigDecimal(8_546.67));

        filingParametersRepository.save(filingParameters);
        filingParametersRepository.save(filingParameters2);
        filingParametersRepository.save(filingParameters3);

        // when - action or the behaviour that we are going to test
        List<FilingParameters> filingParametersList = filingParametersRepository.findByUserId(15);

        // then - verify the output
        assertThat(filingParametersList).isNotNull();
        assertThat(filingParametersList.size()).isEqualTo(2);
    }
}
