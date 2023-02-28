package org.liubov.statetaxcalculator.repository;

import org.liubov.statetaxcalculator.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
