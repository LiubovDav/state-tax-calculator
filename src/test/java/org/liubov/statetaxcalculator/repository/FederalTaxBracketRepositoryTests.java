package org.liubov.statetaxcalculator.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.liubov.statetaxcalculator.model.FederalTaxBracket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FederalTaxBracketRepositoryTests {

    @Autowired
    private FederalTaxBracketRepository federalTaxBracketRepository;

    @DisplayName("JUnit test for find FederalTaxBracket list by year and filing status operation")
    @Test
    void givenFederalTaxBracketList_whenFindByYearAndStateAndFilingStatus_thenReturnFederalTaxBracketList() {
        // given - precondition or setup
        FederalTaxBracket federalTaxBracket = new FederalTaxBracket();
        federalTaxBracket.setYear(2022);
        federalTaxBracket.setFilingStatus("Single");
        federalTaxBracket.setBracketLower(new BigDecimal(10_000));
        federalTaxBracket.setBracketUpper(new BigDecimal(20_000));
        federalTaxBracket.setAccumulatedAmount(new BigDecimal(12_312.00));
        federalTaxBracket.setRate(new BigDecimal(15.54));

        FederalTaxBracket federalTaxBracket2 = new FederalTaxBracket();
        federalTaxBracket2.setYear(2021);
        federalTaxBracket2.setFilingStatus("Single");
        federalTaxBracket2.setBracketLower(new BigDecimal(10_000));
        federalTaxBracket2.setBracketUpper(new BigDecimal(20_000));
        federalTaxBracket2.setAccumulatedAmount(new BigDecimal(12_312.00));
        federalTaxBracket2.setRate(new BigDecimal(18.56));

        FederalTaxBracket federalTaxBracket3 = new FederalTaxBracket();
        federalTaxBracket3.setYear(2022);
        federalTaxBracket3.setFilingStatus("Single");
        federalTaxBracket3.setBracketLower(new BigDecimal(15_000));
        federalTaxBracket3.setBracketUpper(new BigDecimal(25_000));
        federalTaxBracket3.setAccumulatedAmount(new BigDecimal(12_312.00));
        federalTaxBracket3.setRate(new BigDecimal(13.46));

        federalTaxBracketRepository.save(federalTaxBracket);
        federalTaxBracketRepository.save(federalTaxBracket2);
        federalTaxBracketRepository.save(federalTaxBracket3);

        // when - action or the behaviour that we are going to test
        List<FederalTaxBracket> federalTaxBracketList = federalTaxBracketRepository.findByYearAndFilingStatusOrderByBracketLowerDesc(2022, "Single");

        // then - verify the output
        assertThat(federalTaxBracketList).isNotNull();
        assertThat(federalTaxBracketList.size()).isEqualTo(2);
    }
}
