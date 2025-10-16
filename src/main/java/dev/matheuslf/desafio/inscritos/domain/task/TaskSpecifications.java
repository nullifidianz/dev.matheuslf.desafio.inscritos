package dev.matheuslf.desafio.inscritos.domain.task;

import java.util.UUID;
import org.springframework.data.jpa.domain.Specification;
import dev.matheuslf.desafio.inscritos.domain.shared.Specifications;

/**
 * Specifications para consultas complexas de tarefas.
 * Implementa o padrão Specification do DDD para encapsular lógica de consulta.
 */
public class TaskSpecifications {

    private TaskSpecifications() {
        // Classe utilitária
    }

    /**
     * Specification para filtrar tarefas por status
     */
    public static Specification<Task> hasStatus(Task.TaskStatus status) {
        return status != null
                ? (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status)
                : Specifications.alwaysTrue();
    }

    /**
     * Specification para filtrar tarefas por prioridade
     */
    public static Specification<Task> hasPriority(Task.TaskPriority priority) {
        return priority != null
                ? (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("priority"), priority)
                : Specifications.alwaysTrue();
    }

    /**
     * Specification para filtrar tarefas por projeto
     */
    public static Specification<Task> belongsToProject(UUID projectId) {
        return projectId != null
                ? (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("project").get("id"), projectId)
                : Specifications.alwaysTrue();
    }

    /**
     * Specification para filtrar tarefas por prioridade usando string
     */
    public static Specification<Task> hasPriorityString(String priority) {
        if (priority == null || priority.trim().isEmpty()) {
            return Specifications.alwaysTrue();
        }

        try {
            Task.TaskPriority taskPriority = Task.TaskPriority.valueOf(priority.toUpperCase());
            return hasPriority(taskPriority);
        } catch (IllegalArgumentException e) {
            return Specifications.alwaysFalse();
        }
    }

    /**
     * Specification combinada para múltiplos filtros
     */
    public static Specification<Task> withFilters(Task.TaskStatus status, String priority, UUID projectId) {
        return hasStatus(status)
                .and(hasPriorityString(priority))
                .and(belongsToProject(projectId));
    }
}
