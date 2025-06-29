package com.jaianper.teamtasker.team.infrastructure.persistence.repository;

import com.jaianper.teamtasker.team.domain.model.TeamMember;
import com.jaianper.teamtasker.team.domain.model.TeamMemberRole;
import com.jaianper.teamtasker.team.domain.repository.TeamMemberRepository;
import com.jaianper.teamtasker.team.infrastructure.persistence.mapper.TeamMemberEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PostgresTeamMemberRepository implements TeamMemberRepository {

    private final TeamMemberJpaRepository teamMemberRepository;
    private final TeamMemberEntityMapper teamMemberMapper;

    @Override
    public TeamMember save(TeamMember teamMember) {
        return teamMemberMapper.toDomain(teamMemberRepository.save(teamMemberMapper.toEntity(teamMember)));
    }

    @Override
    public List<TeamMember> findByTeamId(UUID teamId) {
        return teamMemberRepository.findByTeamId(teamId).stream().map(teamMemberMapper::toDomain).toList();
    }

    @Override
    public Optional<TeamMember> findByTeamIdAndUserId(UUID teamId, UUID userId) {
        return teamMemberRepository.findByTeamIdAndUserId(teamId, userId)
                .map(teamMemberMapper::toDomain);
    }

    @Override
    public List<TeamMember> findByUserId(UUID userId) {
        return teamMemberRepository.findByUserId(userId).stream().map(teamMemberMapper::toDomain).toList();
    }

    @Override
    public boolean existsByTeamIdAndUserId(UUID teamId, UUID userId) {
        return teamMemberRepository.existsByTeamIdAndUserId(teamId, userId);
    }

    @Override
    public void deleteByTeamIdAndUserId(UUID teamId, UUID userId) {
        teamMemberRepository.deleteByTeamIdAndUserId(teamId, userId);
    }

    @Override
    public List<TeamMember> findByTeamIdAndRole(UUID teamId, TeamMemberRole role) {
        return teamMemberRepository.findByTeamIdAndRole(teamId, role).stream().map(teamMemberMapper::toDomain).toList();
    }
} 