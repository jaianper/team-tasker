package com.jaianper.teamtasker.task.entrypoint.controller;

import com.jaianper.teamtasker.task.application.usecase.CreateTaskUseCase;
import com.jaianper.teamtasker.task.entrypoint.mapper.TaskDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateTaskUseCase createTaskUseCase;

    @MockBean
    private TaskDtoMapper mapper;

    @Test
    void shouldReturn201WhenTaskCreatedSuccessfully() throws Exception {
        /*CreateTaskRequestDTO request = new CreateTaskRequestDTO("Título", "desc", TaskStatus.TODO, TaskPriority.MEDIUM, "juan", null);
        Task domainTask = Task.builder().title("Título").build();
        TaskResponseDTO responseDTO = new TaskResponseDTO("Título", "desc", TaskStatus.TODO, TaskPriority.MEDIUM, "juan", null); // completar

        when(mapper.toDomain(any())).thenReturn(domainTask);
        when(createTaskUseCase.execute(domainTask)).thenReturn(domainTask);
        when(mapper.toDto(domainTask)).thenReturn(responseDTO);

        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated());*/
    }
}