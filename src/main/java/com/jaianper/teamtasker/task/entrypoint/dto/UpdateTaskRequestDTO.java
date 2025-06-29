package com.jaianper.teamtasker.task.entrypoint.dto;

import com.jaianper.teamtasker.task.domain.model.TaskPriority;
import com.jaianper.teamtasker.task.domain.model.TaskStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTaskRequestDTO {
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede superar los 100 caracteres")
    private String title;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String description;

    @NotNull(message = "El estado es obligatorio")
    private TaskStatus status;

    @NotNull(message = "La prioridad es obligatoria")
    private TaskPriority priority;

    @NotBlank(message = "El asignado no puede estar vacío")
    private String assignee;

    @Future(message = "La fecha de vencimiento debe ser futura")
    private LocalDateTime dueDate;
}
