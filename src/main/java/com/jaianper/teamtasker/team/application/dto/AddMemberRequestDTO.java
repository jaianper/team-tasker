package com.jaianper.teamtasker.team.application.dto;

import com.jaianper.teamtasker.team.domain.model.TeamMemberRole;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMemberRequestDTO {
    @NotNull(message = "El ID del usuario es obligatorio")
    private UUID userId;

    private TeamMemberRole role = TeamMemberRole.MEMBER; // Default role
} 