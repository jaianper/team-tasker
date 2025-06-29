package com.jaianper.teamtasker.team.infrastructure.persistence.repository;

import com.jaianper.teamtasker.team.infrastructure.persistence.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamJpaRepository extends JpaRepository<TeamEntity, UUID> {
    Optional<TeamEntity> findByName(String name);
    List<TeamEntity> findByCreatedBy(UUID createdBy);
    boolean existsByName(String name);
} 