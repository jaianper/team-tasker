package com.jaianper.teamtasker.task.infrastructure.persistence.repository;

import com.jaianper.teamtasker.task.domain.model.Task;
import com.jaianper.teamtasker.task.domain.model.TaskPriority;
import com.jaianper.teamtasker.task.domain.model.TaskStatus;
import com.jaianper.teamtasker.task.domain.repository.TaskRepository;
import com.jaianper.teamtasker.task.infrastructure.persistence.mapper.TaskEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PostgresTaskRepository implements TaskRepository {

    private final TaskJpaRepository taskRepository;
    private final TaskEntityMapper taskMapper;

    @Override
    public Task save(Task task) {
        return taskMapper.toDomain(taskRepository.save(taskMapper.toEntity(task)));
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDomain);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll().stream().map(taskMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }

    @Override
    public boolean existsByTitleAndAssignee(String title, String assignee) {
        return taskRepository.existsByTitleAndAssignee(title, assignee);
    }

    @Override
    public List<Task> findByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status).stream().map(taskMapper::toDomain).toList();
    }

    @Override
    public List<Task> findByAssignee(String assignee) {
        return taskRepository.findByAssignee(assignee).stream().map(taskMapper::toDomain).toList();
    }

    @Override
    public List<Task> findByPriority(TaskPriority priority) {
        return taskRepository.findByPriority(priority).stream().map(taskMapper::toDomain).toList();
    }

    @Override
    public List<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority) {
        return taskRepository.findByStatusAndPriority(status, priority).stream().map(taskMapper::toDomain).toList();
    }

    @Override
    public List<Task> findByAssigneeAndStatus(String assignee, TaskStatus status) {
        return taskRepository.findByAssigneeAndStatus(assignee, status).stream().map(taskMapper::toDomain).toList();
    }
}
