package com.jaianper.teamtasker.team.application.usecase;

import com.jaianper.teamtasker.team.domain.model.Team;
import com.jaianper.teamtasker.team.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTeamsUseCase {
    private final TeamRepository teamRepository;

    public List<Team> execute() {
        return teamRepository.findAll();
    }
} 