package com.jaianper.teamtasker.core.application.usecase;

import com.jaianper.teamtasker.core.domain.exception.TaskAlreadyExistsException;
import com.jaianper.teamtasker.core.domain.model.Task;
import com.jaianper.teamtasker.core.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTaskUseCase {
    private final TaskRepository taskRepository;

    public Task execute(Task task) {
        boolean exists = taskRepository.existsByTitleAndAssignee(task.getTitle(), task.getAssignee());

        if (exists) {
            throw new TaskAlreadyExistsException("Ya existe una tarea con el título '" +
                    task.getTitle() + "' asignada a " + task.getAssignee());
        }

        return taskRepository.save(task);
    }
}
