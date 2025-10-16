package dev.matheuslf.desafio.inscritos.infrastructure;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import dev.matheuslf.desafio.inscritos.domain.project.ProjectCreatedEvent;
import dev.matheuslf.desafio.inscritos.domain.task.TaskCreatedEvent;
import dev.matheuslf.desafio.inscritos.domain.task.TaskStatusChangedEvent;

/**
 * Event Handler para processar eventos de domínio.
 * Demonstra como os Domain Events podem ser utilizados para comunicação entre
 * bounded contexts.
 */
@Component
@Slf4j
public class DomainEventHandler {

    @EventListener
    public void handleProjectCreated(ProjectCreatedEvent event) {
        log.info("Projeto criado: ID={}, Nome={}", event.getProjectId(), event.getProjectName());
        // Aqui poderiam ser implementadas ações como:
        // - Envio de notificações
        // - Atualização de índices de busca
        // - Integração com sistemas externos
        // - Auditoria
    }

    @EventListener
    public void handleTaskCreated(TaskCreatedEvent event) {
        log.info("Tarefa criada: ID={}, Título={}, Projeto={}",
                event.getTaskId(), event.getTaskTitle(), event.getProjectId());
        // Aqui poderiam ser implementadas ações como:
        // - Notificação do responsável pelo projeto
        // - Atualização de métricas
        // - Integração com sistemas de gestão
    }

    @EventListener
    public void handleTaskStatusChanged(TaskStatusChangedEvent event) {
        log.info("Status da tarefa alterado: ID={}, {} -> {}, Projeto={}",
                event.getTaskId(), event.getOldStatus(), event.getNewStatus(), event.getProjectId());
        // Aqui poderiam ser implementadas ações como:
        // - Notificação de mudança de status
        // - Atualização de dashboards
        // - Verificação de regras de negócio
        // - Integração com sistemas de workflow
    }
}
