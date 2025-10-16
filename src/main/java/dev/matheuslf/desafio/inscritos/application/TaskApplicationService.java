package dev.matheuslf.desafio.inscritos.application;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import dev.matheuslf.desafio.inscritos.domain.task.TaskService;
import dev.matheuslf.desafio.inscritos.domain.task.Task;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskRequest;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskResponse;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskStatusUpdateRequest;

/**
 * Application Service responsável por orquestrar casos de uso relacionados a
 * tarefas.
 * Esta camada coordena entre os serviços de domínio e a infraestrutura.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TaskApplicationService {

    private final TaskService taskService;

    /**
     * Caso de uso: Criar uma nova tarefa
     */
    public TaskResponse createTask(TaskRequest request) {
        return taskService.createTask(request);
    }

    /**
     * Caso de uso: Listar tarefas com filtros e paginação
     */
    @Transactional(readOnly = true)
    public Page<TaskResponse> getTasksWithFilters(Task.TaskStatus status, String priority, UUID projectId,
            Pageable pageable) {
        return taskService.getTasksWithFilters(status, priority, projectId, pageable);
    }

    /**
     * Caso de uso: Listar tarefas sem paginação
     */
    @Transactional(readOnly = true)
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * Caso de uso: Buscar tarefa por ID
     */
    @Transactional(readOnly = true)
    public TaskResponse getTaskById(UUID id) {
        return taskService.getTaskById(id);
    }

    /**
     * Caso de uso: Atualizar status da tarefa
     */
    public TaskResponse updateTaskStatus(UUID id, TaskStatusUpdateRequest request) {
        return taskService.updateTaskStatus(id, request);
    }

    /**
     * Caso de uso: Remover tarefa
     */
    public void deleteTask(UUID id) {
        taskService.deleteTask(id);
    }
}
