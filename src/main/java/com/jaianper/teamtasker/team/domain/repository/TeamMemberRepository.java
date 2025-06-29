package com.jaianper.teamtasker.team.domain.repository;

import com.jaianper.teamtasker.team.domain.model.TeamMember;
import com.jaianper.teamtasker.team.domain.model.TeamMemberRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamMemberRepository {
    TeamMember save(TeamMember teamMember);
    List<TeamMember> findByTeamId(UUID teamId);
    Optional<TeamMember> findByTeamIdAndUserId(UUID teamId, UUID userId);
    List<TeamMember> findByUserId(UUID userId);
    boolean existsByTeamIdAndUserId(UUID teamId, UUID userId);
    void deleteByTeamIdAndUserId(UUID teamId, UUID userId);
    List<TeamMember> findByTeamIdAndRole(UUID teamId, TeamMemberRole role);
} 