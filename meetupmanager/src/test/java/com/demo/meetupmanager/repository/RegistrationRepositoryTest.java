package com.demo.meetupmanager.repository;

import com.demo.meetupmanager.model.Registration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class RegistrationRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    RegistrationRepository repository;

    @Test
    @DisplayName("Should return true when exists a registration already created")
    public void returnTrueWhenRegistrationExists() {
        String registration = "123";

        Registration registration_class_attribute = createNewRegistration(registration);

        testEntityManager.persist(registration_class_attribute);

        boolean exist = repository.existsByRegistration(registration);

        assertThat(exist).isTrue();

    }
    @Test
    @DisplayName("Should return false when doesn't exists a registration_class_attribute with a registration already created ")
    public void returnFalseWhenRegistrationDoesNotExists() {
        String registration = "123";

        boolean exist = repository.existsByRegistration(registration);

        assertThat(exist).isFalse();

    }

    public static Registration createNewRegistration(String registration) {
        return Registration.builder().name("henrique").dateOfRegistration("10/02/2000").registration(registration).build();
    }
}
