package com.jaianper.teamtasker.task.application.usecase;

import com.jaianper.teamtasker.task.domain.exception.TaskNotFoundException;
import com.jaianper.teamtasker.task.domain.model.Task;
import com.jaianper.teamtasker.task.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
