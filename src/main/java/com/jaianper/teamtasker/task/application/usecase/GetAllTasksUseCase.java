package com.jaianper.teamtasker.task.application.usecase;

import com.jaianper.teamtasker.task.domain.model.Task;
import com.jaianper.teamtasker.task.domain.repository.TaskRepository;
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
