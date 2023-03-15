package org.liubov.statetaxcalculator.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.liubov.statetaxcalculator.model.StateTaxBracket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        stateTaxBracket.setBracketLower(10_000);
        stateTaxBracket.setBracketUpper(20_000);
        stateTaxBracket.setAccumulatedAmount(12_312.00);
        stateTaxBracket.setRate(15.54);

        StateTaxBracket stateTaxBracket2 = new StateTaxBracket();
        stateTaxBracket2.setYear(2022);
        stateTaxBracket2.setState("CA");
        stateTaxBracket2.setFilingStatus("Single");
        stateTaxBracket2.setBracketLower(10_000);
        stateTaxBracket2.setBracketUpper(20_000);
        stateTaxBracket2.setAccumulatedAmount(12_312.00);
        stateTaxBracket2.setRate(18.56);

        StateTaxBracket stateTaxBracket3 = new StateTaxBracket();
        stateTaxBracket3.setYear(2022);
        stateTaxBracket3.setState("NY");
        stateTaxBracket3.setFilingStatus("Single");
        stateTaxBracket3.setBracketLower(15_000);
        stateTaxBracket3.setBracketUpper(25_000);
        stateTaxBracket3.setAccumulatedAmount(12_312.00);
        stateTaxBracket3.setRate(13.46);

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
