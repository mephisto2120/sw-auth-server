package com.tryton.small_world.auth.converter;

import com.tryton.small_world.auth.db.StatusEntity;
import com.tryton.small_world.auth.model.Status;
import com.tryton.small_world.auth.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class StatusToStatusEntityConverter {

    private final StatusRepository statusRepository;

    public StatusEntity toEntity(Status status) {
        return statusRepository.findByStsName(status.name());
    }
}
