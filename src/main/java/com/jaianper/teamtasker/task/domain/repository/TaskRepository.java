package com.jaianper.teamtasker.task.domain.repository;

import com.jaianper.teamtasker.task.domain.model.Task;
import com.jaianper.teamtasker.task.domain.model.TaskPriority;
import com.jaianper.teamtasker.task.domain.model.TaskStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(UUID id);
    List<Task> findAll();
    void deleteById(UUID id);
    boolean existsByTitleAndAssignee(String title, String assignee);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByAssignee(String assignee);
    List<Task> findByPriority(TaskPriority priority);
    List<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority);
    List<Task> findByAssigneeAndStatus(String assignee, TaskStatus status);
}
