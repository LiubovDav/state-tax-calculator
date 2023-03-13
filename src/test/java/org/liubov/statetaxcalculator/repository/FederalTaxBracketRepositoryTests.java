package org.liubov.statetaxcalculator.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.liubov.statetaxcalculator.model.FederalTaxBracket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FederalTaxBracketRepositoryTests {

    @Autowired
    private FederalTaxBracketRepository federalTaxBracketRepository;

    @DisplayName("JUnit test for find FederalTaxBracket list by year and filling status operation")
    @Test
    void givenFederalTaxBracketList_whenFindByYearAndStateAndFillingStatus_thenReturnFederalTaxBracketList() {
        // given - precondition or setup
        FederalTaxBracket federalTaxBracket = new FederalTaxBracket();
        federalTaxBracket.setYear(2022);
        federalTaxBracket.setFillingStatus("Single");
        federalTaxBracket.setBracketLower(10_000);
        federalTaxBracket.setBracketUpper(20_000);
        federalTaxBracket.setAccumulatedAmount(12_312.00);
        federalTaxBracket.setRate(15.54);

        FederalTaxBracket federalTaxBracket2 = new FederalTaxBracket();
        federalTaxBracket2.setYear(2021);
        federalTaxBracket2.setFillingStatus("Single");
        federalTaxBracket2.setBracketLower(10_000);
        federalTaxBracket2.setBracketUpper(20_000);
        federalTaxBracket2.setAccumulatedAmount(12_312.00);
        federalTaxBracket2.setRate(18.56);

        FederalTaxBracket federalTaxBracket3 = new FederalTaxBracket();
        federalTaxBracket3.setYear(2022);
        federalTaxBracket3.setFillingStatus("Single");
        federalTaxBracket3.setBracketLower(15_000);
        federalTaxBracket3.setBracketUpper(25_000);
        federalTaxBracket3.setAccumulatedAmount(12_312.00);
        federalTaxBracket3.setRate(13.46);

        federalTaxBracketRepository.save(federalTaxBracket);
        federalTaxBracketRepository.save(federalTaxBracket2);
        federalTaxBracketRepository.save(federalTaxBracket3);

        // when - action or the behaviour that we are going to test
        List<FederalTaxBracket> federalTaxBracketList = federalTaxBracketRepository.findByYearAndFillingStatusOrderByBracketLowerDesc(2022, "Single");

        // then - verify the output
        assertThat(federalTaxBracketList).isNotNull();
        assertThat(federalTaxBracketList.size()).isEqualTo(2);
    }
}
