package com.jaianper.teamtasker.core.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaianper.teamtasker.core.application.usecase.CreateTaskUseCase;
import com.jaianper.teamtasker.core.domain.model.Task;
import com.jaianper.teamtasker.core.domain.model.TaskPriority;
import com.jaianper.teamtasker.core.domain.model.TaskStatus;
import com.jaianper.teamtasker.core.entrypoint.dto.CreateTaskRequestDTO;
import com.jaianper.teamtasker.core.entrypoint.dto.TaskResponseDTO;
import com.jaianper.teamtasker.core.entrypoint.mapper.TaskDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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