package org.liubov.statetaxcalculator.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.liubov.statetaxcalculator.model.StateTaxBracket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StateTaxBracketRepositoryTests {

    @Autowired
    private StateTaxBracketRepository stateTaxBracketRepository;

    @DisplayName("JUnit test for find StateTaxBracket list by year and state and filing status operation")
    @Test
    void givenStateTaxBracketList_whenFindByYearAndStateAndFilingStatus_thenReturnStateTaxBracketList() {
        // given - precondition or setup
        StateTaxBracket stateTaxBracket = new StateTaxBracket();
        stateTaxBracket.setYear(2022);
        stateTaxBracket.setState("CA");
        stateTaxBracket.setFilingStatus("Single");
        stateTaxBracket.setBracketLower(new BigDecimal(10_000));
        stateTaxBracket.setBracketUpper(new BigDecimal(20_000));
        stateTaxBracket.setAccumulatedAmount(new BigDecimal(12_312.00));
        stateTaxBracket.setRate(new BigDecimal(15.54));

        StateTaxBracket stateTaxBracket2 = new StateTaxBracket();
        stateTaxBracket2.setYear(2022);
        stateTaxBracket2.setState("CA");
        stateTaxBracket2.setFilingStatus("Single");
        stateTaxBracket2.setBracketLower(new BigDecimal(10_000));
        stateTaxBracket2.setBracketUpper(new BigDecimal(20_000));
        stateTaxBracket2.setAccumulatedAmount(new BigDecimal(12_312.00));
        stateTaxBracket2.setRate(new BigDecimal(18.56));

        StateTaxBracket stateTaxBracket3 = new StateTaxBracket();
        stateTaxBracket3.setYear(2022);
        stateTaxBracket3.setState("NY");
        stateTaxBracket3.setFilingStatus("Single");
        stateTaxBracket3.setBracketLower(new BigDecimal(15_000));
        stateTaxBracket3.setBracketUpper(new BigDecimal(25_000));
        stateTaxBracket3.setAccumulatedAmount(new BigDecimal(12_312.00));
        stateTaxBracket3.setRate(new BigDecimal(13.46));

        stateTaxBracketRepository.save(stateTaxBracket);
        stateTaxBracketRepository.save(stateTaxBracket2);
        stateTaxBracketRepository.save(stateTaxBracket3);

        // when - action or the behaviour that we are going to test
        List<StateTaxBracket> stateTaxBracketList = stateTaxBracketRepository.findByYearAndStateAndFilingStatusOrderByBracketLowerDesc(2022, "CA", "Single");

        // then - verify the output
        assertThat(stateTaxBracketList).isNotNull();
        assertThat(stateTaxBracketList.size()).isEqualTo(2);
    }
}
