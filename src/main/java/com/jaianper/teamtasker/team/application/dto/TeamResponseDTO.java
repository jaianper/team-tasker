package com.jaianper.teamtasker.team.application.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private UUID createdBy;
    private List<TeamMemberResponseDTO> members;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 