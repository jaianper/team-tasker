package com.jaianper.teamtasker.team.application.dto;

import com.jaianper.teamtasker.team.domain.model.TeamMemberRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberResponseDTO {
    private UUID id;
    private UUID teamId;
    private UUID userId;
    private TeamMemberRole role;
    private LocalDateTime joinedAt;
    private LocalDateTime updatedAt;
} 