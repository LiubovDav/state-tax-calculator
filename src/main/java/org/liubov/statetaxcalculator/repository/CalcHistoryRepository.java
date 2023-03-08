package org.liubov.statetaxcalculator.repository;

import org.liubov.statetaxcalculator.model.CalcHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalcHistoryRepository extends CrudRepository<CalcHistory, Integer> {

    List<CalcHistory> findByUserId(Integer userId);
}
