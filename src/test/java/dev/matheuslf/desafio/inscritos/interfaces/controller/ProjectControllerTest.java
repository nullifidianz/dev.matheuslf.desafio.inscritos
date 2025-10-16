package dev.matheuslf.desafio.inscritos.interfaces.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.matheuslf.desafio.inscritos.domain.project.ProjectService;
import dev.matheuslf.desafio.inscritos.interfaces.dto.project.ProjectRequest;
import dev.matheuslf.desafio.inscritos.interfaces.dto.project.ProjectResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private ProjectService projectService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void createProject_ShouldReturnCreatedProject_WhenValidRequest() throws Exception {
                // Arrange
                UUID projectId = UUID.randomUUID();
                ProjectRequest request = new ProjectRequest(
                                "Projeto Teste",
                                "Descrição do projeto",
                                LocalDate.now(),
                                LocalDate.now().plusDays(30));

                ProjectResponse response = new ProjectResponse(
                                projectId,
                                "Projeto Teste",
                                "Descrição do projeto",
                                LocalDate.now(),
                                LocalDate.now().plusDays(30));

                when(projectService.createProject(any(ProjectRequest.class))).thenReturn(response);

                // Act & Assert
                mockMvc.perform(post("/projects")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(projectId.toString()))
                                .andExpect(jsonPath("$.name").value("Projeto Teste"));
        }

        @Test
        void getAllProjects_ShouldReturnProjects_WhenCalled() throws Exception {
                // Act & Assert
                mockMvc.perform(get("/projects"))
                                .andExpect(status().isOk());
        }

        @Test
        void getProjectById_ShouldReturnProject_WhenProjectExists() throws Exception {
                // Arrange
                UUID projectId = UUID.randomUUID();
                ProjectResponse response = new ProjectResponse(
                                projectId,
                                "Projeto Teste",
                                "Descrição do projeto",
                                LocalDate.now(),
                                LocalDate.now().plusDays(30));

                when(projectService.getProjectById(projectId)).thenReturn(response);

                // Act & Assert
                mockMvc.perform(get("/projects/{id}", projectId))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(projectId.toString()))
                                .andExpect(jsonPath("$.name").value("Projeto Teste"));
        }
}
