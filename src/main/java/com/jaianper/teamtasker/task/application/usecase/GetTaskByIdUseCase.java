package com.jaianper.teamtasker.task.application.usecase;

import com.jaianper.teamtasker.task.domain.model.Task;
import com.jaianper.teamtasker.task.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetTaskByIdUseCase {
    private final TaskRepository taskRepository;

    public Optional<Task> execute(UUID id) {
        return taskRepository.findById(id);
    }
}
