package com.jaianper.teamtasker.core.infrastructure.persistence.repository;

import com.jaianper.teamtasker.core.domain.model.Task;
import com.jaianper.teamtasker.core.domain.repository.TaskRepository;
import com.jaianper.teamtasker.core.infrastructure.persistence.mapper.TaskEntityMapper;
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
}
