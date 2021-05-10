package com.tryton.small_world.auth.repository;

import com.tryton.small_world.auth.db.RoleEntity;
import com.tryton.small_world.auth.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@SpringBootTest
class RoleRepositoryIntegrationTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void shouldFindUserByRolName() {
        //given
        String roleName = Role.USER.getRoleName();

        //when
        RoleEntity roleEntity = roleRepository.findByRolName(roleName);

        //then
        assertThat(roleEntity.getRolName()).isEqualTo(roleName);
        assertThat(roleEntity.getRolId()).isGreaterThan(0L);
    }
}
