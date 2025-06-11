package com.jaianper.teamtasker.core.domain.repository;

import com.jaianper.teamtasker.core.domain.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(UUID id);
    List<Task> findAll();
    void deleteById(UUID id);
    boolean existsByTitleAndAssignee(String title, String assignee);
    //List<Task> findByStatus(TaskStatus status);
    //List<Task> findByAssignee(String userId);
}
