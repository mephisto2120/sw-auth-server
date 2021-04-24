package com.tryton.small_world.auth.controller.repository;

import com.tryton.small_world.auth.controller.db.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryIntegrationTest {

    private static final String SPASSKY_EMAIL = "boris.spassky@gmail.com";
    private static final String PETROSIAN_EMAIL = "tigran.petrosian@gmail.com";
    private static final String SPASSKY_PASSWORD = "borja";
    private static final String PETROSIAN_PASSWORD = "tigr";

    private final List<Long> userIds = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        UserEntity user1 = new UserEntity(null, SPASSKY_EMAIL, SPASSKY_PASSWORD);
        UserEntity user2 = new UserEntity(null, PETROSIAN_EMAIL, PETROSIAN_PASSWORD);
        //save user, verify has ID value after save
        assertNull(user1.getId());
        assertNull(user2.getId());//null before save

        UserEntity saved1 = userRepository.save(user1);
        UserEntity saved2 = userRepository.save(user2);

        assertNotNull(user1.getId());
        assertNotNull(user2.getId());

        userIds.add(saved1.getId());
        userIds.add(saved2.getId());
    }

    @AfterEach
    void cleanUp() {
        userIds.forEach(userRepository::deleteById);
    }

    @Test
    public void testFetchData(){
        assertUser(SPASSKY_EMAIL, SPASSKY_PASSWORD);
        assertUser(PETROSIAN_EMAIL, PETROSIAN_PASSWORD);

        Iterable<UserEntity> users = userRepository.findAll();
        assertThat(users).hasSize(2);
    }

    private void assertUser(String email, String password) {
        UserEntity user = userRepository.findByEmail(email);

        assertNotNull(user);
        assertThat(user.getPassword()).isEqualTo(password);
    }
}
