package com.jaianper.teamtasker.core.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
public class Task {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private TaskStatus status;

    @JsonProperty("priority")
    private TaskPriority priority;

    @JsonProperty("assignee")
    private String assignee; //User

    @JsonProperty("dueDate")
    private LocalDateTime dueDate;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    public Task updateFrom(Task source) {
        return this.toBuilder()
                //.id(source.getId())
                .title(source.getTitle())
                .description(source.getDescription())
                .status(source.getStatus())
                .priority(source.getPriority())
                .assignee(source.getAssignee())
                .dueDate(source.getDueDate())
                //.createdAt(source.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
