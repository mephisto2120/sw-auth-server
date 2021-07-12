package com.tryton.small_world.auth.repository;

import com.tryton.small_world.auth.db.StatusEntity;
import com.tryton.small_world.auth.model.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@SpringBootTest
class StatusRepositoryIntegrationTest {

    @Autowired
    private StatusRepository statusRepository;

    @Test
    void shouldFindStatusByAbbreviation() {
        //given
        String statusAbbreviation = Status.ACTIVE.getAbbreviation();

        //when
        StatusEntity statusEntity = statusRepository.findByStsName(statusAbbreviation);

        //then
        assertThat(statusEntity.getStsName()).isEqualTo(statusAbbreviation);
        assertThat(statusEntity.getStsId()).isGreaterThan(0L);
    }
}
