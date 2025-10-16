package dev.matheuslf.desafio.inscritos.domain.task;

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

import dev.matheuslf.desafio.inscritos.domain.project.ProjectService;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskRequest;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskResponse;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskStatusUpdateRequest;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private TaskRequest taskRequest;
    private UUID taskId;
    private UUID projectId;

    @BeforeEach
    void setUp() {
        taskId = UUID.randomUUID();
        projectId = UUID.randomUUID();

        dev.matheuslf.desafio.inscritos.domain.project.Project project = new dev.matheuslf.desafio.inscritos.domain.project.Project();
        project.setId(projectId);
        project.setName("Projeto Teste");

        task = new Task();
        task.setId(taskId);
        task.setTitle("Tarefa Teste");
        task.setDescription("Descrição da tarefa teste");
        task.setStatus(Task.TaskStatus.TODO);
        task.setPriority(Task.TaskPriority.MEDIUM);
        task.setDueDate(LocalDate.now().plusDays(7));
        task.setProject(project);

        taskRequest = new TaskRequest(
                "Tarefa Teste",
                "Descrição da tarefa teste",
                Task.TaskStatus.TODO,
                Task.TaskPriority.MEDIUM,
                LocalDate.now().plusDays(7),
                projectId);
    }

    @Test
    void createTask_ShouldReturnTaskResponse_WhenValidRequest() {
        // Arrange
        when(projectService.existsById(projectId)).thenReturn(true);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Act
        TaskResponse response = taskService.createTask(taskRequest);

        // Assert
        assertNotNull(response);
        assertEquals(taskId, response.id());
        assertEquals("Tarefa Teste", response.title());
        assertEquals(Task.TaskStatus.TODO, response.status());
        verify(projectService).existsById(projectId);
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void createTask_ShouldThrowException_WhenProjectDoesNotExist() {
        // Arrange
        when(projectService.existsById(projectId)).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> taskService.createTask(taskRequest));

        assertEquals("Projeto não encontrado com ID: " + projectId, exception.getMessage());
        verify(projectService).existsById(projectId);
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void getAllTasks_ShouldReturnListOfTasks_WhenCalled() {
        // Arrange
        List<Task> tasks = List.of(task);
        when(taskRepository.findAll()).thenReturn(tasks);

        // Act
        List<TaskResponse> response = taskService.getAllTasks();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(taskId, response.get(0).id());
        verify(taskRepository).findAll();
    }

    @Test
    void updateTaskStatus_ShouldReturnUpdatedTaskResponse_WhenTaskExists() {
        // Arrange
        TaskStatusUpdateRequest statusUpdate = new TaskStatusUpdateRequest(Task.TaskStatus.DOING);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Act
        TaskResponse response = taskService.updateTaskStatus(taskId, statusUpdate);

        // Assert
        assertNotNull(response);
        assertEquals(taskId, response.id());
        verify(taskRepository).findById(taskId);
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void updateTaskStatus_ShouldThrowException_WhenTaskDoesNotExist() {
        // Arrange
        TaskStatusUpdateRequest statusUpdate = new TaskStatusUpdateRequest(Task.TaskStatus.DOING);
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> taskService.updateTaskStatus(taskId, statusUpdate));

        assertEquals("Tarefa não encontrada com ID: " + taskId, exception.getMessage());
        verify(taskRepository).findById(taskId);
        verify(taskRepository, never()).save(any(Task.class));
    }
}
