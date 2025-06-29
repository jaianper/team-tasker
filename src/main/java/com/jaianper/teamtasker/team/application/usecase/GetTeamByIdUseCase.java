package com.jaianper.teamtasker.team.application.usecase;

import com.jaianper.teamtasker.team.domain.exception.TeamNotFoundException;
import com.jaianper.teamtasker.team.domain.model.Team;
import com.jaianper.teamtasker.team.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetTeamByIdUseCase {
    private final TeamRepository teamRepository;

    public Optional<Team> execute(UUID id) {
        return teamRepository.findById(id);
    }

    public Team executeOrThrow(UUID id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("No se encontró el equipo con ID: " + id));
    }
} 