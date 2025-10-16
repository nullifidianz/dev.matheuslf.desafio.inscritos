package dev.matheuslf.desafio.inscritos.interfaces.dto.task;

import java.time.LocalDate;
import java.util.UUID;
import dev.matheuslf.desafio.inscritos.domain.task.Task.TaskPriority;
import dev.matheuslf.desafio.inscritos.domain.task.Task.TaskStatus;

public record TaskResponse(
        UUID id,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDate dueDate,
        UUID projectId,
        String projectName) {

}
