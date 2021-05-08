package com.tryton.small_world.auth.converter;

import com.tryton.small_world.auth.db.StatusEntity;
import com.tryton.small_world.auth.model.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusEntityConverter {
    public Status toModel(StatusEntity entity) {
        return Status.of(entity.getStsName());
    }
}
