package com.jaianper.teamtasker.team.application.usecase;

import com.jaianper.teamtasker.team.domain.exception.MemberNotFoundException;
import com.jaianper.teamtasker.team.domain.exception.TeamNotFoundException;
import com.jaianper.teamtasker.team.domain.repository.TeamRepository;
import com.jaianper.teamtasker.team.domain.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RemoveMemberFromTeamUseCase {
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    public void execute(UUID teamId, UUID userId) {
        // Check if team exists
        if (!teamRepository.findById(teamId).isPresent()) {
            throw new TeamNotFoundException("No se encontró el equipo con ID: " + teamId);
        }

        // Check if member exists
        if (!teamMemberRepository.existsByTeamIdAndUserId(teamId, userId)) {
            throw new MemberNotFoundException("El usuario no es miembro del equipo");
        }

        // Remove member from team
        teamMemberRepository.deleteByTeamIdAndUserId(teamId, userId);
    }
} 