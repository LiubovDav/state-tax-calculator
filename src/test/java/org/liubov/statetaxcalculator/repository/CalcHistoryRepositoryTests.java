package org.liubov.statetaxcalculator.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.liubov.statetaxcalculator.model.CalcHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
@DataJpaTest
class CalcHistoryRepositoryTests {
    @Autowired
    private CalcHistoryRepository calcHistoryRepository;

    @Disabled
    @DisplayName("JUnit test for find CalcHistory list by user id operation")
    @Test
    void givenCalcHistoryList_whenFindByUserId_thenReturnCalcHistoryList() {
        // given - precondition or setup
        CalcHistory calcHistory = new CalcHistory();
        calcHistory.setUserId(15);
        calcHistory.setYear(2022);
        calcHistory.setState("CA");
        calcHistory.setFillingStatus("Single");
        calcHistory.setIncome(135_000);
        calcHistory.setStateTaxAmount(7_000.45);
        calcHistory.setFederalTaxAmount(10_345.43);

        CalcHistory calcHistory2 = new CalcHistory();
        calcHistory2.setUserId(34);
        calcHistory2.setYear(2022);
        calcHistory2.setState("NY");
        calcHistory2.setFillingStatus("Single");
        calcHistory2.setIncome(100_000);
        calcHistory2.setStateTaxAmount(6_567.67);
        calcHistory2.setFederalTaxAmount(8_456.89);

        CalcHistory calcHistory3 = new CalcHistory();
        calcHistory3.setUserId(15);
        calcHistory3.setYear(2021);
        calcHistory3.setState("CA");
        calcHistory3.setFillingStatus("Single");
        calcHistory3.setIncome(124_000);
        calcHistory3.setStateTaxAmount(6_098.34);
        calcHistory3.setFederalTaxAmount(8_546.67);

        calcHistoryRepository.save(calcHistory);
        calcHistoryRepository.save(calcHistory2);
        calcHistoryRepository.save(calcHistory3);

        // when - action or the behaviour that we are going to test
        List<CalcHistory> calcHistoryList = calcHistoryRepository.findByUserId(15);

        // then - verify the output
        assertThat(calcHistoryList).isNotNull();
        assertThat(calcHistoryList.size()).isEqualTo(2);
    }
}
