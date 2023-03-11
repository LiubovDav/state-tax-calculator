package org.liubov.statetaxcalculator.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.liubov.statetaxcalculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Disabled
    @DisplayName("JUnit test for find user by email operation")
    @Test
    void givenUserObject_whenFindByEmail_thenReturnUserObject() {
        // given - precondition or setup
        User user = new User();
        user.setEmail("john@gmail.com");
        user.setPassword("1234");
        user.setName("John");

        userRepository.save(user);

        // when - action or the behaviour that we are going to test
        User userDb = userRepository.findByEmail("john@gmail.com").get();

        // then - verify the output
        assertThat(userDb).isNotNull();
        assertThat(userDb.getEmail()).isEqualTo(user.getEmail());
    }
}
