package dev.matheuslf.desafio.inscritos.domain.project;

import java.util.UUID;
import dev.matheuslf.desafio.inscritos.domain.shared.DomainEvent;

/**
 * Evento de domínio disparado quando um projeto é criado.
 */
public class ProjectCreatedEvent extends DomainEvent {

    private final UUID projectId;
    private final String projectName;

    public ProjectCreatedEvent(UUID projectId, String projectName) {
        super("ProjectCreated");
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
}
