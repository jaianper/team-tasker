package com.jaianper.teamtasker.core.entrypoint.controller;

import com.jaianper.teamtasker.core.application.usecase.*;
import com.jaianper.teamtasker.core.domain.model.Task;
import com.jaianper.teamtasker.core.entrypoint.dto.CreateTaskRequestDTO;
import com.jaianper.teamtasker.core.entrypoint.dto.TaskResponseDTO;
import com.jaianper.teamtasker.core.entrypoint.dto.UpdateTaskRequestDTO;
import com.jaianper.teamtasker.core.entrypoint.mapper.TaskDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Tag(name = "Tasks", description = "Operaciones para gestionar tareas")
@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetAllTasksUseCase getAllTasksUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;

    private final TaskDtoMapper taskDtoMapper;

    @Operation(summary = "Crear una nueva tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarea creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody CreateTaskRequestDTO requestDTO) {
        Task created = createTaskUseCase.execute(taskDtoMapper.toDomain(requestDTO));
        //return new ResponseEntity<>(taskDtoMapper.toDto(created), HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDtoMapper.toDto(created));
    }

    @Operation(summary = "Listar todas las tareas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tareas listadas exitosamente"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<Task> tasks = getAllTasksUseCase.execute();
        List<TaskResponseDTO> dtos = tasks.stream().map(taskDtoMapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Obtener tarea por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable UUID id) {
        Optional<Task> task = getTaskByIdUseCase.execute(id);
        return task.map(value -> ResponseEntity.ok(taskDtoMapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Actualizar una tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable UUID id, @Valid @RequestBody UpdateTaskRequestDTO requestDTO) {
        Task taskToUpdate = taskDtoMapper.toDomain(requestDTO);
        taskToUpdate.setId(id);
        Task updated = updateTaskUseCase.execute(taskToUpdate);
        return ResponseEntity.ok(taskDtoMapper.toDto(updated));
    }

    @Operation(summary = "Eliminar una tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea eliminada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        deleteTaskUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
