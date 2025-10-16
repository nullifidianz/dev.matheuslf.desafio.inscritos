package dev.matheuslf.desafio.inscritos.interfaces.dto.task;

import jakarta.validation.constraints.NotNull;
import dev.matheuslf.desafio.inscritos.domain.task.Task.TaskStatus;

public record TaskStatusUpdateRequest(
        @NotNull(message = "Status é obrigatório") TaskStatus status) {

}
