package dev.matheuslf.desafio.inscritos.interfaces.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.matheuslf.desafio.inscritos.domain.task.TaskService;
import dev.matheuslf.desafio.inscritos.domain.task.Task.TaskPriority;
import dev.matheuslf.desafio.inscritos.domain.task.Task.TaskStatus;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskRequest;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskResponse;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskStatusUpdateRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private TaskService taskService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void createTask_ShouldReturnCreatedTask_WhenValidRequest() throws Exception {
                // Arrange
                UUID taskId = UUID.randomUUID();
                UUID projectId = UUID.randomUUID();

                TaskRequest request = new TaskRequest(
                                "Tarefa Teste",
                                "Descrição da tarefa",
                                TaskStatus.TODO,
                                TaskPriority.MEDIUM,
                                LocalDate.now().plusDays(7),
                                projectId);

                TaskResponse response = new TaskResponse(
                                taskId,
                                "Tarefa Teste",
                                "Descrição da tarefa",
                                TaskStatus.TODO,
                                TaskPriority.MEDIUM,
                                LocalDate.now().plusDays(7),
                                projectId,
                                "Projeto Teste");

                when(taskService.createTask(any(TaskRequest.class))).thenReturn(response);

                // Act & Assert
                mockMvc.perform(post("/tasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(taskId.toString()))
                                .andExpect(jsonPath("$.title").value("Tarefa Teste"))
                                .andExpect(jsonPath("$.status").value("TODO"));
        }

        @Test
        void getTasksWithFilters_ShouldReturnTasks_WhenCalled() throws Exception {
                // Act & Assert
                mockMvc.perform(get("/tasks"))
                                .andExpect(status().isOk());
        }

        @Test
        void updateTaskStatus_ShouldReturnUpdatedTask_WhenValidRequest() throws Exception {
                // Arrange
                UUID taskId = UUID.randomUUID();
                TaskStatusUpdateRequest request = new TaskStatusUpdateRequest(TaskStatus.DOING);

                TaskResponse response = new TaskResponse(
                                taskId,
                                "Tarefa Teste",
                                "Descrição da tarefa",
                                TaskStatus.DOING,
                                TaskPriority.MEDIUM,
                                LocalDate.now().plusDays(7),
                                UUID.randomUUID(),
                                "Projeto Teste");

                when(taskService.updateTaskStatus(any(UUID.class), any(TaskStatusUpdateRequest.class)))
                                .thenReturn(response);

                // Act & Assert
                mockMvc.perform(put("/tasks/{id}/status", taskId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(taskId.toString()))
                                .andExpect(jsonPath("$.status").value("DOING"));
        }
}
