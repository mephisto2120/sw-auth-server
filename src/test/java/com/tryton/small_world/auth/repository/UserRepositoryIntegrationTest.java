package com.tryton.small_world.auth.repository;

import com.tryton.small_world.auth.db.UsersEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        cleanUpByEmails();

        Date now = Date.from(Instant.now());
        UsersEntity user1 = UsersEntity.builder()
                .usrEmail(SPASSKY_EMAIL)
                .usrPassword(SPASSKY_PASSWORD)
                .usrFirstName("Boris")
                .usrLastName("Spassky")
                .usrDateInserted(now.toString())
                .usrDateModified(now)
                .build();
        UsersEntity user2 = UsersEntity.builder()
                .usrEmail(PETROSIAN_EMAIL)
                .usrPassword(PETROSIAN_PASSWORD)
                .usrFirstName("Tigran")
                .usrLastName("Petrosian")
                .usrDateInserted(now.toString())
                .usrDateModified(now)
                .build();
        //save user, verify has ID value after save
        assertNull(user1.getUsrId());
        assertNull(user2.getUsrId());//null before save

        UsersEntity saved1 = userRepository.save(user1);
        UsersEntity saved2 = userRepository.save(user2);

        assertNotNull(user1.getUsrId());
        assertNotNull(user2.getUsrId());

        userIds.add(saved1.getUsrId());
        userIds.add(saved2.getUsrId());
    }

    private void cleanUpByEmails() {
        deleteByEmail(SPASSKY_EMAIL);
        deleteByEmail(PETROSIAN_EMAIL);
    }

    private void deleteByEmail(String email) {
        UsersEntity spasskyEntity = userRepository.findByUsrEmail(email);
        Optional.ofNullable(spasskyEntity).ifPresent(userRepository::delete);
    }

    @AfterEach
    void cleanUp() {
        userIds.forEach(userRepository::deleteById);
    }

    @Test
    public void testFetchData() {
        assertUser(SPASSKY_EMAIL, SPASSKY_PASSWORD);
        assertUser(PETROSIAN_EMAIL, PETROSIAN_PASSWORD);
    }

    private void assertUser(String email, String password) {
        UsersEntity user = userRepository.findByUsrEmail(email);

        assertNotNull(user);
        assertThat(user.getUsrPassword()).isEqualTo(password);
    }
}
