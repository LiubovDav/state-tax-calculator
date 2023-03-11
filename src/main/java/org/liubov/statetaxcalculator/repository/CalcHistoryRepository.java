package org.liubov.statetaxcalculator.repository;

import org.liubov.statetaxcalculator.model.CalcHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalcHistoryRepository extends JpaRepository<CalcHistory, Integer> {

    List<CalcHistory> findByUserId(Integer userId);
}
