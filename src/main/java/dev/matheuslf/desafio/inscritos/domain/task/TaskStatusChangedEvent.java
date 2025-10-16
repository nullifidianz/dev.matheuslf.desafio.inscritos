package dev.matheuslf.desafio.inscritos.domain.task;

import java.util.UUID;
import dev.matheuslf.desafio.inscritos.domain.shared.DomainEvent;

/**
 * Evento de domínio disparado quando o status de uma tarefa é alterado.
 */
public class TaskStatusChangedEvent extends DomainEvent {

    private final UUID taskId;
    private final Task.TaskStatus oldStatus;
    private final Task.TaskStatus newStatus;
    private final UUID projectId;

    public TaskStatusChangedEvent(UUID taskId, Task.TaskStatus oldStatus, Task.TaskStatus newStatus, UUID projectId) {
        super("TaskStatusChanged");
        this.taskId = taskId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.projectId = projectId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public Task.TaskStatus getOldStatus() {
        return oldStatus;
    }

    public Task.TaskStatus getNewStatus() {
        return newStatus;
    }

    public UUID getProjectId() {
        return projectId;
    }
}
