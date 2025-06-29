package com.jaianper.teamtasker.team.application.usecase;

import com.jaianper.teamtasker.team.domain.exception.TeamNotFoundException;
import com.jaianper.teamtasker.team.domain.model.TeamMember;
import com.jaianper.teamtasker.team.domain.repository.TeamRepository;
import com.jaianper.teamtasker.team.domain.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetTeamMembersUseCase {
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    public List<TeamMember> execute(UUID teamId) {
        // Check if team exists
        if (!teamRepository.findById(teamId).isPresent()) {
            throw new TeamNotFoundException("No se encontró el equipo con ID: " + teamId);
        }

        return teamMemberRepository.findByTeamId(teamId);
    }
} 