package dev.matheuslf.desafio.inscritos.interfaces.dto.task;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import dev.matheuslf.desafio.inscritos.domain.task.Task.TaskPriority;
import dev.matheuslf.desafio.inscritos.domain.task.Task.TaskStatus;

public record TaskRequest(
        @NotBlank(message = "Título é obrigatório") @Size(min = 5, max = 150, message = "Título deve ter entre 5 e 150 caracteres") String title,
        String description,
        @NotNull(message = "Status é obrigatório") TaskStatus status,
        @NotNull(message = "Prioridade é obrigatória") TaskPriority priority,
        LocalDate dueDate,
        @NotNull(message = "ID do projeto é obrigatório") UUID projectId) {

}
