package org.liubov.statetaxcalculator.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.liubov.statetaxcalculator.model.TaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaxRateRepositoryTests {

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Disabled
    @DisplayName("JUnit test for find TaxRate list by state and filling status operation")
    @Test
    void givenTaxRateList_whenFindByStateAndFillingStatus_thenReturnTaxRateList() {
        // given - precondition or setup
        TaxRate taxRate = new TaxRate();
        taxRate.setYear(2021);
        taxRate.setState("CA");
        taxRate.setFillingStatus("Single");
        taxRate.setBracket(10_000);
        taxRate.setRate(15.54);

        TaxRate taxRate2 = new TaxRate();
        taxRate2.setYear(2022);
        taxRate2.setState("CA");
        taxRate2.setFillingStatus("Single");
        taxRate2.setBracket(23_000);
        taxRate2.setRate(18.56);

        TaxRate taxRate3 = new TaxRate();
        taxRate3.setYear(2022);
        taxRate3.setState("NY");
        taxRate3.setFillingStatus("Single");
        taxRate3.setBracket(15_00);
        taxRate3.setRate(13.46);

        taxRateRepository.save(taxRate);
        taxRateRepository.save(taxRate2);
        taxRateRepository.save(taxRate3);

        // when - action or the behaviour that we are going to test
        List<TaxRate> taxRateList = taxRateRepository.findByStateAndFillingStatus("CA", "Single");

        // then - verify the output
        assertThat(taxRateList).isNotNull();
        assertThat(taxRateList.size()).isEqualTo(2);
    }
}
