package org.liubov.statetaxcalculator.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.liubov.statetaxcalculator.model.FillingParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
@DataJpaTest
class FillingParametersRepositoryTests {
    @Autowired
    private FillingParametersRepository fillingParametersRepository;

    @DisplayName("JUnit test for find FillingParameters list by user id operation")
    @Test
    void givenFillingParametersList_whenFindByUserId_thenReturnFillingParametersList() {
        // given - precondition or setup
        FillingParameters fillingParameters = new FillingParameters();
        fillingParameters.setUserId(15);
        fillingParameters.setYear(2022);
        fillingParameters.setState("CA");
        fillingParameters.setFillingStatus("Single");
        fillingParameters.setIncome(135_000);
        fillingParameters.setStateTaxAmount(7_000.45);
        fillingParameters.setFederalTaxAmount(10_345.43);

        FillingParameters fillingParameters2 = new FillingParameters();
        fillingParameters2.setUserId(34);
        fillingParameters2.setYear(2022);
        fillingParameters2.setState("NY");
        fillingParameters2.setFillingStatus("Single");
        fillingParameters2.setIncome(100_000);
        fillingParameters2.setStateTaxAmount(6_567.67);
        fillingParameters2.setFederalTaxAmount(8_456.89);

        FillingParameters fillingParameters3 = new FillingParameters();
        fillingParameters3.setUserId(15);
        fillingParameters3.setYear(2021);
        fillingParameters3.setState("CA");
        fillingParameters3.setFillingStatus("Single");
        fillingParameters3.setIncome(124_000);
        fillingParameters3.setStateTaxAmount(6_098.34);
        fillingParameters3.setFederalTaxAmount(8_546.67);

        fillingParametersRepository.save(fillingParameters);
        fillingParametersRepository.save(fillingParameters2);
        fillingParametersRepository.save(fillingParameters3);

        // when - action or the behaviour that we are going to test
        List<FillingParameters> fillingParametersList = fillingParametersRepository.findByUserId(15);

        // then - verify the output
        assertThat(fillingParametersList).isNotNull();
        assertThat(fillingParametersList.size()).isEqualTo(2);
    }
}
