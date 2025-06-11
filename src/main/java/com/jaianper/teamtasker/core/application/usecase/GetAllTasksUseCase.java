package com.jaianper.teamtasker.core.application.usecase;

import com.jaianper.teamtasker.core.domain.model.Task;
import com.jaianper.teamtasker.core.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTasksUseCase {
    private final TaskRepository taskRepository;

    public List<Task> execute() {
        return taskRepository.findAll();
    }
}
