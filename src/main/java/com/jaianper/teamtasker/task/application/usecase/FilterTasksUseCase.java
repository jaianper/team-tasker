package com.jaianper.teamtasker.task.application.usecase;

import com.jaianper.teamtasker.task.domain.model.Task;
import com.jaianper.teamtasker.task.domain.model.TaskPriority;
import com.jaianper.teamtasker.task.domain.model.TaskStatus;
import com.jaianper.teamtasker.task.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterTasksUseCase {
    private final TaskRepository taskRepository;

    public List<Task> execute(TaskStatus status, TaskPriority priority, String assignee) {
        // If all filters are provided
        if (status != null && priority != null && assignee != null) {
            return taskRepository.findByStatusAndPriority(status, priority)
                    .stream()
                    .filter(task -> assignee.equals(task.getAssignee()))
                    .toList();
        }
        
        // If status and priority are provided
        if (status != null && priority != null) {
            return taskRepository.findByStatusAndPriority(status, priority);
        }
        
        // If status and assignee are provided
        if (status != null && assignee != null) {
            return taskRepository.findByAssigneeAndStatus(assignee, status);
        }
        
        // If only status is provided
        if (status != null) {
            return taskRepository.findByStatus(status);
        }
        
        // If only priority is provided
        if (priority != null) {
            return taskRepository.findByPriority(priority);
        }
        
        // If only assignee is provided
        if (assignee != null) {
            return taskRepository.findByAssignee(assignee);
        }
        
        // If no filters are provided, return all tasks
        return taskRepository.findAll();
    }
} 