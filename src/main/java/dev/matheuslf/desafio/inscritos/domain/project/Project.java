package dev.matheuslf.desafio.inscritos.domain.project;

import java.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import dev.matheuslf.desafio.inscritos.domain.shared.BaseAuditoryInfo;
import dev.matheuslf.desafio.inscritos.domain.task.Task;

/**
 * Aggregate Root para o contexto de Projetos.
 * Um projeto é um aggregate que contém tarefas e coordena suas operações.
 */
@Entity
@Table(name = "tb_projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseAuditoryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "project_id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    @Size(min = 3, max = 100)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    /**
     * Método de domínio para adicionar uma tarefa ao projeto.
     * Garante a consistência do aggregate.
     */
    public void addTask(Task task) {
        if (task != null) {
            task.setProject(this);
            this.tasks.add(task);
        }
    }

    /**
     * Método de domínio para remover uma tarefa do projeto.
     * Garante a consistência do aggregate.
     */
    public void removeTask(Task task) {
        if (task != null) {
            this.tasks.remove(task);
            task.setProject(null);
        }
    }

    /**
     * Retorna uma lista imutável das tarefas do projeto.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Método de domínio para verificar se o projeto está ativo.
     */
    public boolean isActive() {
        return endDate == null || endDate.isAfter(LocalDate.now());
    }

    /**
     * Método de domínio para verificar se o projeto pode ser finalizado.
     */
    public boolean canBeCompleted() {
        return tasks.stream().allMatch(task -> task.getStatus() == Task.TaskStatus.DONE);
    }
}