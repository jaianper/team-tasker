package com.jaianper.teamtasker.team.application.usecase;

import com.jaianper.teamtasker.team.domain.exception.TeamNotFoundException;
import com.jaianper.teamtasker.team.domain.repository.TeamRepository;
import com.jaianper.teamtasker.team.domain.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteTeamUseCase {
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    public void execute(UUID id) {
        if (!teamRepository.findById(id).isPresent()) {
            throw new TeamNotFoundException("No se encontró el equipo con ID: " + id);
        }
        
        // Delete all team members first
        teamMemberRepository.findByTeamId(id).forEach(member -> 
            teamMemberRepository.deleteByTeamIdAndUserId(id, member.getUserId())
        );
        
        // Delete the team
        teamRepository.deleteById(id);
    }
} 