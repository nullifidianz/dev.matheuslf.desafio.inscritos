package dev.matheuslf.desafio.inscritos.domain.task;

import java.util.UUID;
import dev.matheuslf.desafio.inscritos.domain.shared.DomainEvent;

/**
 * Evento de domínio disparado quando uma tarefa é criada.
 */
public class TaskCreatedEvent extends DomainEvent {

    private final UUID taskId;
    private final String taskTitle;
    private final UUID projectId;

    public TaskCreatedEvent(UUID taskId, String taskTitle, UUID projectId) {
        super("TaskCreated");
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.projectId = projectId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public UUID getProjectId() {
        return projectId;
    }
}
