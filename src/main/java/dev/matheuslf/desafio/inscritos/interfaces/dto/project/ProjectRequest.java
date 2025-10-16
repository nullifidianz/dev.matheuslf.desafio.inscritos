package dev.matheuslf.desafio.inscritos.interfaces.dto.project;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectRequest(
        @NotBlank(message = "Nome é obrigatório") @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres") String name,
        String description,
        LocalDate startDate,
        LocalDate endDate) {

}
