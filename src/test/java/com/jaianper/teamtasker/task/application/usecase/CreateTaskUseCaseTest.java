package com.jaianper.teamtasker.task.application.usecase;

import com.jaianper.teamtasker.task.domain.exception.TaskValidationException;
import com.jaianper.teamtasker.task.domain.model.Task;
import com.jaianper.teamtasker.task.domain.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Pruebas Unitarias de Casos de Uso
 */
class CreateTaskUseCaseTest {
    private TaskRepository repository;
    private CreateTaskUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(TaskRepository.class);
        useCase = new CreateTaskUseCase(repository);
    }

    @Test
    void shouldCreateTaskSuccessfully() {
        Task task = Task.builder()
                .title("Test")
                .assignee("user1")
                .dueDate(LocalDateTime.now().plusDays(1))
                .build();

        when(repository.existsByTitleAndAssignee(task.getTitle(), task.getAssignee()))
                .thenReturn(false);
        when(repository.save(task)).thenReturn(task);

        Task result = useCase.execute(task);

        assertEquals(task, result);
        verify(repository).save(task);
    }

    @Test
    void shouldThrowValidationExceptionWhenDueDateInPast() {
        Task task = Task.builder()
                .title("Test")
                .assignee("user1")
                .dueDate(LocalDateTime.now().minusDays(1))
                .build();

        TaskValidationException ex = assertThrows(TaskValidationException.class,
                () -> useCase.execute(task));

        assertEquals("La fecha de vencimiento no puede estar en el pasado.", ex.getMessage());
    }
}
