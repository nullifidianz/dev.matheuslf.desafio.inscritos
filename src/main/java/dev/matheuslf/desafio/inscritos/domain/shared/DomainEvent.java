package dev.matheuslf.desafio.inscritos.domain.shared;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Classe base para todos os eventos de domínio.
 * Implementa o padrão Domain Events do DDD.
 */
public abstract class DomainEvent {

    private final UUID eventId;
    private final LocalDateTime occurredOn;
    private final String eventType;

    protected DomainEvent(String eventType) {
        this.eventId = UUID.randomUUID();
        this.occurredOn = LocalDateTime.now();
        this.eventType = eventType;
    }

    public UUID getEventId() {
        return eventId;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    public String getEventType() {
        return eventType;
    }
}
