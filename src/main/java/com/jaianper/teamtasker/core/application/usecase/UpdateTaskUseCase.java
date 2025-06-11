package com.jaianper.teamtasker.core.application.usecase;

import com.jaianper.teamtasker.core.domain.exception.TaskNotFoundException;
import com.jaianper.teamtasker.core.domain.model.Task;
import com.jaianper.teamtasker.core.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateTaskUseCase {

    private final TaskRepository taskRepository;

    public Task execute(Task task) {
        Task existing = taskRepository.findById(task.getId())
                .orElseThrow(() -> new TaskNotFoundException("No se encontró la tarea con ID: " + task.getId()));

        Task updated = existing.updateFrom(task);
        return taskRepository.save(updated);
    }
}
