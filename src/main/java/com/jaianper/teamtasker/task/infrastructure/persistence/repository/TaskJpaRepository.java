package com.jaianper.teamtasker.task.infrastructure.persistence.repository;

import com.jaianper.teamtasker.task.domain.model.TaskPriority;
import com.jaianper.teamtasker.task.domain.model.TaskStatus;
import com.jaianper.teamtasker.task.infrastructure.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, UUID> {
    boolean existsByTitleAndAssignee(String title, String assignee);
    List<TaskEntity> findByStatus(TaskStatus status);
    List<TaskEntity> findByAssignee(String assignee);
    List<TaskEntity> findByPriority(TaskPriority priority);
    List<TaskEntity> findByStatusAndPriority(TaskStatus status, TaskPriority priority);
    List<TaskEntity> findByAssigneeAndStatus(String assignee, TaskStatus status);
}
