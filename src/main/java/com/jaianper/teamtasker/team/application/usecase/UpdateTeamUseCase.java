package com.jaianper.teamtasker.team.application.usecase;

import com.jaianper.teamtasker.team.domain.exception.TeamNotFoundException;
import com.jaianper.teamtasker.team.domain.exception.TeamValidationException;
import com.jaianper.teamtasker.team.domain.model.Team;
import com.jaianper.teamtasker.team.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateTeamUseCase {
    private final TeamRepository teamRepository;

    public Team execute(Team team) {
        validateTeam(team);
        
        Team existing = teamRepository.findById(team.getId())
                .orElseThrow(() -> new TeamNotFoundException("No se encontró el equipo con ID: " + team.getId()));

        // Check if name is being changed and if it already exists
        if (!existing.getName().equals(team.getName()) && 
            teamRepository.existsByName(team.getName())) {
            throw new TeamValidationException("Ya existe un equipo con el nombre '" + team.getName() + "'");
        }

        // Update team with new data
        Team updatedTeam = existing.updateFrom(team);
        updatedTeam.setUpdatedAt(LocalDateTime.now());

        return teamRepository.save(updatedTeam);
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