package com.jaianper.teamtasker.task.application.usecase;

import com.jaianper.teamtasker.task.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteTaskUseCase {
    private final TaskRepository taskRepository;

    public void execute(UUID id) {
        taskRepository.deleteById(id);
    }
}
