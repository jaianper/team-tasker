package com.jaianper.teamtasker.team.application.usecase;

import com.jaianper.teamtasker.team.domain.exception.TeamAlreadyExistsException;
import com.jaianper.teamtasker.team.domain.exception.TeamValidationException;
import com.jaianper.teamtasker.team.domain.model.Team;
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
public class CreateTeamUseCase {
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    public Team execute(Team team) {
        validateTeam(team);
        
        // Check if team already exists
        if (teamRepository.existsByName(team.getName())) {
            throw new TeamAlreadyExistsException("Ya existe un equipo con el nombre '" + team.getName() + "'");
        }

        // Set default values
        Team teamToSave = team.toBuilder()
                .id(UUID.randomUUID())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Team savedTeam = teamRepository.save(teamToSave);

        // Add creator as admin member
        if (team.getCreatedBy() != null) {
            TeamMember creatorMember = TeamMember.builder()
                    .id(UUID.randomUUID())
                    .teamId(savedTeam.getId())
                    .userId(team.getCreatedBy())
                    .role(TeamMemberRole.ADMIN)
                    .joinedAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            
            teamMemberRepository.save(creatorMember);
        }

        return savedTeam;
    }

    private void validateTeam(Team team) {
        if (team.getName() == null || team.getName().trim().isEmpty()) {
            throw new TeamValidationException("El nombre del equipo es obligatorio");
        }
        
        if (team.getName().length() < 3) {
            throw new TeamValidationException("El nombre del equipo debe tener al menos 3 caracteres");
        }
        
        if (team.getName().length() > 100) {
            throw new TeamValidationException("El nombre del equipo no puede superar los 100 caracteres");
        }
        
        if (team.getDescription() != null && team.getDescription().length() > 500) {
            throw new TeamValidationException("La descripción del equipo no puede superar los 500 caracteres");
        }
    }
} 