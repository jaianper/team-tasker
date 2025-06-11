package com.jaianper.teamtasker.core.entrypoint.dto;

import com.jaianper.teamtasker.core.domain.model.TaskPriority;
import com.jaianper.teamtasker.core.domain.model.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private String assignee;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
