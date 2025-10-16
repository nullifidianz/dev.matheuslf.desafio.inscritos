package dev.matheuslf.desafio.inscritos.domain.task;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import dev.matheuslf.desafio.inscritos.domain.project.ProjectService;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskRequest;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskResponse;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskStatusUpdateRequest;
import dev.matheuslf.desafio.inscritos.infrastructure.DomainEventConfig.DomainEventPublisher;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final DomainEventPublisher domainEventPublisher;

    public TaskResponse createTask(TaskRequest request) {
        if (!projectService.existsById(request.projectId())) {
            throw new RuntimeException("Projeto não encontrado com ID: " + request.projectId());
        }

        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setPriority(request.priority());
        task.setDueDate(request.dueDate());

        dev.matheuslf.desafio.inscritos.domain.project.Project project = new dev.matheuslf.desafio.inscritos.domain.project.Project();
        project.setId(request.projectId());
        task.setProject(project);

        Task savedTask = taskRepository.save(task);

        // Publica evento de domínio
        domainEventPublisher
                .publish(new TaskCreatedEvent(savedTask.getId(), savedTask.getTitle(), savedTask.getProject().getId()));

        return mapToResponse(savedTask);
    }

    @Transactional(readOnly = true)
    public Page<TaskResponse> getTasksWithFilters(Task.TaskStatus status, String priority, UUID projectId,
            Pageable pageable) {
        if (priority != null && !priority.trim().isEmpty()) {
            try {
            } catch (IllegalArgumentException e) {
                // Se a prioridade for inválida, retorna página vazia
                return Page.empty(pageable);
            }
        }

        Specification<Task> spec = TaskSpecifications.withFilters(status, priority, projectId);
        Page<Task> tasks = taskRepository.findAll(spec, pageable);
        return tasks.map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse updateTaskStatus(UUID taskId, TaskStatusUpdateRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + taskId));

        Task.TaskStatus oldStatus = task.getStatus();
        task.setStatus(request.status());
        Task updatedTask = taskRepository.save(task);

        // Publica evento de domínio
        domainEventPublisher.publish(new TaskStatusChangedEvent(updatedTask.getId(), oldStatus, updatedTask.getStatus(),
                updatedTask.getProject().getId()));

        return mapToResponse(updatedTask);
    }

    public void deleteTask(UUID taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Tarefa não encontrada com ID: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }

    @Transactional(readOnly = true)
    public TaskResponse getTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));
        return mapToResponse(task);
    }

    private TaskResponse mapToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getProject().getId(),
                task.getProject().getName());
    }
}
