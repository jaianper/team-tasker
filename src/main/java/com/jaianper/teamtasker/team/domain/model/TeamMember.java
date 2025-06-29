package com.jaianper.teamtasker.team.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TeamMember {
    private UUID id;
    private UUID teamId;
    private UUID userId;
    private TeamMemberRole role;
    private LocalDateTime joinedAt;
    private LocalDateTime updatedAt;

    public TeamMember updateFrom(TeamMember source) {
        return this.toBuilder()
                .role(source.getRole())
                .updatedAt(LocalDateTime.now())
                .build();
    }
} 