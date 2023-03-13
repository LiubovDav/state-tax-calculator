package org.liubov.statetaxcalculator.repository;

import org.liubov.statetaxcalculator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
