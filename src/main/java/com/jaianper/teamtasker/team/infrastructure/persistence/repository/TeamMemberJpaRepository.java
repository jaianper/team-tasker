package com.jaianper.teamtasker.team.infrastructure.persistence.repository;

import com.jaianper.teamtasker.team.domain.model.TeamMemberRole;
import com.jaianper.teamtasker.team.infrastructure.persistence.entity.TeamMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamMemberJpaRepository extends JpaRepository<TeamMemberEntity, UUID> {
    List<TeamMemberEntity> findByTeamId(UUID teamId);
    Optional<TeamMemberEntity> findByTeamIdAndUserId(UUID teamId, UUID userId);
    List<TeamMemberEntity> findByUserId(UUID userId);
    boolean existsByTeamIdAndUserId(UUID teamId, UUID userId);
    List<TeamMemberEntity> findByTeamIdAndRole(UUID teamId, TeamMemberRole role);
    
    @Modifying
    @Query("DELETE FROM TeamMemberEntity t WHERE t.teamId = :teamId AND t.userId = :userId")
    void deleteByTeamIdAndUserId(@Param("teamId") UUID teamId, @Param("userId") UUID userId);
} 