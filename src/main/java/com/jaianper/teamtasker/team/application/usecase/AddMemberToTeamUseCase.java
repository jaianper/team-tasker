package com.jaianper.teamtasker.team.application.usecase;

import com.jaianper.teamtasker.team.domain.exception.MemberAlreadyExistsException;
import com.jaianper.teamtasker.team.domain.exception.TeamNotFoundException;
import com.jaianper.teamtasker.team.domain.model.TeamMember;
import com.jaianper.teamtasker.team.domain.model.TeamMemberRole;
import com.jaianper.teamtasker.team.domain.repository.TeamRepository;
import com.jaianper.teamtasker.team.domain.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddMemberToTeamUseCase {
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    public TeamMember execute(UUID teamId, UUID userId, TeamMemberRole role) {
        // Check if team exists
        if (!teamRepository.findById(teamId).isPresent()) {
            throw new TeamNotFoundException("No se encontró el equipo con ID: " + teamId);
        }

        // Check if member already exists
        if (teamMemberRepository.existsByTeamIdAndUserId(teamId, userId)) {
            throw new MemberAlreadyExistsException("El usuario ya es miembro del equipo");
        }

        // Create new team member
        TeamMember teamMember = TeamMember.builder()
                .id(UUID.randomUUID())
                .teamId(teamId)
                .userId(userId)
                .role(role != null ? role : TeamMemberRole.MEMBER)
                .joinedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return teamMemberRepository.save(teamMember);
    }
} 