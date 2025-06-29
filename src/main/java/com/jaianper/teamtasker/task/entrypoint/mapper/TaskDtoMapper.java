package com.jaianper.teamtasker.task.entrypoint.mapper;

import com.jaianper.teamtasker.task.domain.model.Task;
import com.jaianper.teamtasker.task.entrypoint.dto.CreateTaskRequestDTO;
import com.jaianper.teamtasker.task.entrypoint.dto.TaskResponseDTO;
import com.jaianper.teamtasker.task.entrypoint.dto.UpdateTaskRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskDtoMapper {
    TaskDtoMapper INSTANCE = Mappers.getMapper(TaskDtoMapper.class);

    Task toDomain(CreateTaskRequestDTO dto);

    Task toDomain(UpdateTaskRequestDTO dto);

    TaskResponseDTO toDto(Task task);
}
