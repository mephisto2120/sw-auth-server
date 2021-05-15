package com.tryton.small_world.auth.service;

import com.tryton.small_world.auth.db.StatusEntity;
import com.tryton.small_world.auth.db.UsersEntity;
import com.tryton.small_world.auth.db.UsersRolesEntity;
import com.tryton.small_world.auth.model.Role;
import com.tryton.small_world.auth.model.Status;
import com.tryton.small_world.auth.model.User;
import com.tryton.small_world.auth.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;


@ActiveProfiles("test")
@SpringBootTest
class UserServiceIntegrationTest {

    private static final String EMAIL = "faker@gmail.com";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Faker";
    private static final String PASSWORD = "topSecret";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        cleanUp();
    }

    @AfterEach
    void tearDown() {
        cleanUp();
    }

    private void cleanUp() {
        UsersEntity usersEntity = userRepository.findByUsrEmail(EMAIL);
        if (usersEntity != null) {
            removeUserRolesDueToProblemWithHibernate(usersEntity);

            userRepository.delete(usersEntity);
        }
    }

    private void removeUserRolesDueToProblemWithHibernate(UsersEntity usersEntity) {
        String sql = "DELETE FROM auth_test.users_roles WHERE ur_usr_id = " + usersEntity.getUsrId();
        jdbcTemplate.update(sql);
    }

    @Test
    void shouldCreateActiveUser() {
        //given
        User user = user();

        //when
        Throwable thrown = catchThrowable(() -> userService.create(user));

        //then
        assertThat(thrown).isNull();
        UsersEntity usersEntity = userRepository.findByUsrEmail(EMAIL);
        assertThat(usersEntity.getUsrFirstName()).isEqualTo(FIRST_NAME);
        assertThat(usersEntity.getUsrLastName()).isEqualTo(LAST_NAME);

        assertThatUserHasStatusActive(usersEntity);
        assertThatUserHasRoleUser(usersEntity);
    }

    @Test
    void shouldFindUserWhileTryToSignUp() {
        //given
        User user = user();
        userService.create(user);

        //when
        User foundUser = userService.findByEmail(EMAIL);

        //then
        assertUser(foundUser);
    }

    @Test
    void shouldFindUserWhileTryToSignIn() {
        //given
        User user = user();
        userService.create(user);

        User searchedUser = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        //when
        User foundUser = userService.findByEmailAndPassword(searchedUser);

        //then
        assertUser(foundUser);
    }

    private static void assertUser(User foundUser) {
        assertThat(foundUser.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(foundUser.getLastName()).isEqualTo(LAST_NAME);
        assertThat(foundUser.getEmail()).isEqualTo(EMAIL);
        assertThat(foundUser.getPassword()).isEqualTo(PASSWORD);
        assertThat(foundUser.getStatus()).isEqualTo(Status.ACTIVE);
        assertThat(foundUser.getRoles().get(0)).isEqualTo(Role.USER);
    }

    private static User user() {
        return User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();
    }

    private void assertThatUserHasStatusActive(UsersEntity usersEntity) {
        StatusEntity usrStsId = usersEntity.getUsrStsId();
        assertThat(usrStsId.getStsName()).isEqualTo(Status.ACTIVE.getAbbreviation());
    }

    private void assertThatUserHasRoleUser(UsersEntity usersEntity) {
        List<UsersRolesEntity> usersRolesEntities = usersEntity.getUsersRolesEntityList();
        assertThat(usersRolesEntities).hasSize(1);
        assertThat(usersRolesEntities.get(0).getRoleEntity().getRolName()).isEqualTo(Role.USER.getRoleName());
    }
}
