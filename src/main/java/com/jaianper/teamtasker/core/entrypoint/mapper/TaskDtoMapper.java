package com.jaianper.teamtasker.core.entrypoint.mapper;

import com.jaianper.teamtasker.core.domain.model.Task;
import com.jaianper.teamtasker.core.entrypoint.dto.CreateTaskRequestDTO;
import com.jaianper.teamtasker.core.entrypoint.dto.TaskResponseDTO;
import com.jaianper.teamtasker.core.entrypoint.dto.UpdateTaskRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskDtoMapper {
    TaskDtoMapper INSTANCE = Mappers.getMapper(TaskDtoMapper.class);

    Task toDomain(CreateTaskRequestDTO dto);

    Task toDomain(UpdateTaskRequestDTO dto);

    TaskResponseDTO toDto(Task task);
}
