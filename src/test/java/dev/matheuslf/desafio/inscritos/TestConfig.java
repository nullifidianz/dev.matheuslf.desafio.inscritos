package dev.matheuslf.desafio.inscritos;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;
import dev.matheuslf.desafio.inscritos.infrastructure.DomainEventConfig.DomainEventPublisher;

/**
 * Configuração específica para testes que fornece mocks para dependências
 * que não são necessárias em testes de controller.
 * 
 * TODO: Verificar se esta configuração resolve o problema do
 * DomainEventPublisher
 * TODO: Testar se os mocks estão funcionando corretamente
 */
@TestConfiguration
public class TestConfig implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @Bean
    @Primary
    public DomainEventPublisher domainEventPublisher() {
        // TODO: Implementar DomainEventPublisher para testes
        // TODO: Verificar se esta configuração resolve o problema do
        // DomainEventPublisher
        // TODO: Testar se os mocks estão funcionando corretamente
        return null;
    }
}
