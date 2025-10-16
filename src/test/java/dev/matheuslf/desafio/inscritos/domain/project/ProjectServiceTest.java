package dev.matheuslf.desafio.inscritos.domain.project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.matheuslf.desafio.inscritos.interfaces.dto.project.ProjectRequest;
import dev.matheuslf.desafio.inscritos.interfaces.dto.project.ProjectResponse;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project;
    private ProjectRequest projectRequest;
    private UUID projectId;

    @BeforeEach
    void setUp() {
        projectId = UUID.randomUUID();
        project = new Project();
        project.setId(projectId);
        project.setName("Projeto Teste");
        project.setDescription("Descrição do projeto teste");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now().plusDays(30));

        projectRequest = new ProjectRequest(
                "Projeto Teste",
                "Descrição do projeto teste",
                LocalDate.now(),
                LocalDate.now().plusDays(30));
    }

    @Test
    void createProject_ShouldReturnProjectResponse_WhenValidRequest() {
        // Arrange
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // Act
        ProjectResponse response = projectService.createProject(projectRequest);

        // Assert
        assertNotNull(response);
        assertEquals(projectId, response.id());
        assertEquals("Projeto Teste", response.name());
        verify(projectRepository).save(any(Project.class));
    }

    @Test
    void getAllProjects_ShouldReturnListOfProjects_WhenCalled() {
        // Arrange
        List<Project> projects = List.of(project);
        when(projectRepository.findAll()).thenReturn(projects);

        // Act
        List<ProjectResponse> response = projectService.getAllProjects();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(projectId, response.get(0).id());
        verify(projectRepository).findAll();
    }

    @Test
    void getProjectById_ShouldReturnProjectResponse_WhenProjectExists() {
        // Arrange
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // Act
        ProjectResponse response = projectService.getProjectById(projectId);

        // Assert
        assertNotNull(response);
        assertEquals(projectId, response.id());
        assertEquals("Projeto Teste", response.name());
        verify(projectRepository).findById(projectId);
    }

    @Test
    void getProjectById_ShouldThrowException_WhenProjectDoesNotExist() {
        // Arrange
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> projectService.getProjectById(projectId));

        assertEquals("Projeto não encontrado com ID: " + projectId, exception.getMessage());
        verify(projectRepository).findById(projectId);
    }
}
