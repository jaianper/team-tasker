package com.jaianper.teamtasker.core.infrastructure.persistence.repository;

import com.jaianper.teamtasker.core.infrastructure.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, UUID> {
    boolean existsByTitleAndAssignee(String title, String assignee);
}
