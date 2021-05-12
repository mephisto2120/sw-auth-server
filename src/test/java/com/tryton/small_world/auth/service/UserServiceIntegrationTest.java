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
        String firstName = "John";
        String lastName = "Faker";
        User user = User.builder()
                .email(EMAIL)
                .password("topSecret")
                .firstName(firstName)
                .lastName(lastName)
                .build();

        //when
        Throwable thrown = catchThrowable(() -> userService.create(user));

        //then
        assertThat(thrown).isNull();
        UsersEntity usersEntity = userRepository.findByUsrEmail(EMAIL);
        assertThat(usersEntity.getUsrFirstName()).isEqualTo(firstName);
        assertThat(usersEntity.getUsrLastName()).isEqualTo(lastName);

        assertThatUserHasStatusActive(usersEntity);
        assertThatUserHasRoleUser(usersEntity);
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
