package com.jaianper.teamtasker.team.infrastructure.persistence.repository;

import com.jaianper.teamtasker.team.domain.model.Team;
import com.jaianper.teamtasker.team.domain.repository.TeamRepository;
import com.jaianper.teamtasker.team.infrastructure.persistence.mapper.TeamEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PostgresTeamRepository implements TeamRepository {

    private final TeamJpaRepository teamRepository;
    private final TeamEntityMapper teamMapper;

    @Override
    public Team save(Team team) {
        return teamMapper.toDomain(teamRepository.save(teamMapper.toEntity(team)));
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll().stream().map(teamMapper::toDomain).toList();
    }

    @Override
    public Optional<Team> findById(UUID id) {
        return teamRepository.findById(id)
                .map(teamMapper::toDomain);
    }

    @Override
    public List<Team> findByCreatedBy(UUID createdBy) {
        return teamRepository.findByCreatedBy(createdBy).stream().map(teamMapper::toDomain).toList();
    }

    @Override
    public boolean existsByName(String name) {
        return teamRepository.existsByName(name);
    }

    @Override
    public void deleteById(UUID id) {
        teamRepository.deleteById(id);
    }
} 