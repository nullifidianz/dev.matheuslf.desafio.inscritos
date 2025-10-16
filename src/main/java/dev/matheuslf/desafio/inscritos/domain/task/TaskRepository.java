package dev.matheuslf.desafio.inscritos.domain.task;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository para operações de persistência de tarefas.
 * Implementa JpaSpecificationExecutor para suporte ao padrão Specification do
 * DDD.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, UUID>, JpaSpecificationExecutor<Task> {

        List<Task> findByProjectId(UUID projectId);

        boolean existsByProjectId(UUID projectId);
}
