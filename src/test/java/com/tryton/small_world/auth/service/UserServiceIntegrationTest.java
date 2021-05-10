package com.tryton.small_world.auth.service;

import com.tryton.small_world.auth.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;


@ActiveProfiles("test")
@SpringBootTest
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldCreateActiveUser() {
        //given
        User user = User.builder()
                .email("faker@gmail.com")
                .password("topSecret")
                .firstName("John")
                .lastName("Faker")
                .build();

        //when
        Throwable thrown = catchThrowable(() -> userService.create(user));

        //then
        assertThat(thrown).isNull();
    }
}
