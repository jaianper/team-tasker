package com.jaianper.teamtasker.team.domain.repository;

import com.jaianper.teamtasker.team.domain.model.Team;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamRepository {
    Team save(Team team);
    List<Team> findAll();
    Optional<Team> findById(UUID id);
    List<Team> findByCreatedBy(UUID createdBy);
    boolean existsByName(String name);
    void deleteById(UUID id);
} 