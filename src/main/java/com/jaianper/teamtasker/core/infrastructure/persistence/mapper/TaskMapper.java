package com.jaianper.teamtasker.core.infrastructure.persistence.mapper;

import com.jaianper.teamtasker.core.domain.model.Task;
import com.jaianper.teamtasker.core.infrastructure.persistence.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskEntity toEntity(Task task);
    Task toDomain(TaskEntity entity);
}
