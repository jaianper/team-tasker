package com.jaianper.teamtasker.task.application.usecase;

import com.jaianper.teamtasker.task.domain.model.Task;
import com.jaianper.teamtasker.task.domain.model.TaskPriority;
import com.jaianper.teamtasker.task.domain.model.TaskStatus;
import com.jaianper.teamtasker.task.domain.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilterTasksUseCaseTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private FilterTasksUseCase filterTasksUseCase;

    private Task task1;
    private Task task2;
    private Task task3;

    @BeforeEach
    void setUp() {
        task1 = Task.builder()
                .id(UUID.randomUUID())
                .title("Task 1")
                .description("Description 1")
                .status(TaskStatus.TODO)
                .priority(TaskPriority.HIGH)
                .assignee("user1")
                .dueDate(LocalDateTime.now().plusDays(1))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        task2 = Task.builder()
                .id(UUID.randomUUID())
                .title("Task 2")
                .description("Description 2")
                .status(TaskStatus.IN_PROGRESS)
                .priority(TaskPriority.MEDIUM)
                .assignee("user2")
                .dueDate(LocalDateTime.now().plusDays(2))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        task3 = Task.builder()
                .id(UUID.randomUUID())
                .title("Task 3")
                .description("Description 3")
                .status(TaskStatus.DONE)
                .priority(TaskPriority.LOW)
                .assignee("user1")
                .dueDate(LocalDateTime.now().plusDays(3))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void execute_WithNoFilters_ShouldReturnAllTasks() {
        // Given
        List<Task> allTasks = Arrays.asList(task1, task2, task3);
        when(taskRepository.findAll()).thenReturn(allTasks);

        // When
        List<Task> result = filterTasksUseCase.execute(null, null, null);

        // Then
        assertEquals(3, result.size());
        verify(taskRepository).findAll();
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void execute_WithStatusFilter_ShouldReturnTasksWithStatus() {
        // Given
        List<Task> todoTasks = Arrays.asList(task1);
        when(taskRepository.findByStatus(TaskStatus.TODO)).thenReturn(todoTasks);

        // When
        List<Task> result = filterTasksUseCase.execute(TaskStatus.TODO, null, null);

        // Then
        assertEquals(1, result.size());
        assertEquals(TaskStatus.TODO, result.get(0).getStatus());
        verify(taskRepository).findByStatus(TaskStatus.TODO);
    }

    @Test
    void execute_WithPriorityFilter_ShouldReturnTasksWithPriority() {
        // Given
        List<Task> highPriorityTasks = Arrays.asList(task1);
        when(taskRepository.findByPriority(TaskPriority.HIGH)).thenReturn(highPriorityTasks);

        // When
        List<Task> result = filterTasksUseCase.execute(null, TaskPriority.HIGH, null);

        // Then
        assertEquals(1, result.size());
        assertEquals(TaskPriority.HIGH, result.get(0).getPriority());
        verify(taskRepository).findByPriority(TaskPriority.HIGH);
    }

    @Test
    void execute_WithAssigneeFilter_ShouldReturnTasksForAssignee() {
        // Given
        List<Task> user1Tasks = Arrays.asList(task1, task3);
        when(taskRepository.findByAssignee("user1")).thenReturn(user1Tasks);

        // When
        List<Task> result = filterTasksUseCase.execute(null, null, "user1");

        // Then
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(task -> "user1".equals(task.getAssignee())));
        verify(taskRepository).findByAssignee("user1");
    }

    @Test
    void execute_WithStatusAndPriorityFilter_ShouldReturnTasksWithBothCriteria() {
        // Given
        List<Task> todoHighTasks = Arrays.asList(task1);
        when(taskRepository.findByStatusAndPriority(TaskStatus.TODO, TaskPriority.HIGH)).thenReturn(todoHighTasks);

        // When
        List<Task> result = filterTasksUseCase.execute(TaskStatus.TODO, TaskPriority.HIGH, null);

        // Then
        assertEquals(1, result.size());
        assertEquals(TaskStatus.TODO, result.get(0).getStatus());
        assertEquals(TaskPriority.HIGH, result.get(0).getPriority());
        verify(taskRepository).findByStatusAndPriority(TaskStatus.TODO, TaskPriority.HIGH);
    }

    @Test
    void execute_WithStatusAndAssigneeFilter_ShouldReturnTasksWithBothCriteria() {
        // Given
        List<Task> user1TodoTasks = Arrays.asList(task1);
        when(taskRepository.findByAssigneeAndStatus("user1", TaskStatus.TODO)).thenReturn(user1TodoTasks);

        // When
        List<Task> result = filterTasksUseCase.execute(TaskStatus.TODO, null, "user1");

        // Then
        assertEquals(1, result.size());
        assertEquals(TaskStatus.TODO, result.get(0).getStatus());
        assertEquals("user1", result.get(0).getAssignee());
        verify(taskRepository).findByAssigneeAndStatus("user1", TaskStatus.TODO);
    }

    @Test
    void execute_WithAllFilters_ShouldReturnTasksWithAllCriteria() {
        // Given
        List<Task> todoHighTasks = Arrays.asList(task1);
        when(taskRepository.findByStatusAndPriority(TaskStatus.TODO, TaskPriority.HIGH)).thenReturn(todoHighTasks);

        // When
        List<Task> result = filterTasksUseCase.execute(TaskStatus.TODO, TaskPriority.HIGH, "user1");

        // Then
        assertEquals(1, result.size());
        assertEquals(TaskStatus.TODO, result.get(0).getStatus());
        assertEquals(TaskPriority.HIGH, result.get(0).getPriority());
        assertEquals("user1", result.get(0).getAssignee());
        verify(taskRepository).findByStatusAndPriority(TaskStatus.TODO, TaskPriority.HIGH);
    }
} 