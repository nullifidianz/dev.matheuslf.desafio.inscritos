package dev.matheuslf.desafio.inscritos.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;
import dev.matheuslf.desafio.inscritos.domain.shared.DomainEvent;

/**
 * Configuração para publicação de eventos de domínio.
 * Implementa o padrão Domain Events do DDD.
 * 
 * TODO: Verificar se esta configuração está causando problemas nos testes
 * TODO: Considerar usar @Lazy ou outras estratégias para testes
 */
@Configuration
public class DomainEventConfig implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @Bean
    public DomainEventPublisher domainEventPublisher() {
        // TODO: Implementar DomainEventPublisher corretamente
        // TODO: Verificar se esta configuração está causando problemas nos testes
        // TODO: Considerar usar @Lazy ou outras estratégias para testes
        // TODO: Verificar se eventPublisher pode ser null em alguns contextos
        return null;
    }

    /**
     * Interface para publicação de eventos de domínio
     */
    public interface DomainEventPublisher {
        void publish(DomainEvent event);
    }
}
