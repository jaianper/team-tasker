package com.jaianper.teamtasker.task.infrastructure.persistence.mapper;

import com.jaianper.teamtasker.task.domain.model.Task;
import com.jaianper.teamtasker.task.infrastructure.persistence.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskEntityMapper {
    TaskEntity toEntity(Task task);
    Task toDomain(TaskEntity taskEntity);
}
