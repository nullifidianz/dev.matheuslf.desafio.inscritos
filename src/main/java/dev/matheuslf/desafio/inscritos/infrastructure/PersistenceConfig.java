package dev.matheuslf.desafio.inscritos.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configurações de infraestrutura para persistência de dados.
 * Esta camada contém configurações específicas da infraestrutura.
 */
@Configuration
@EnableJpaRepositories(basePackages = "dev.matheuslf.desafio.inscritos.domain")
@EnableTransactionManagement
public class PersistenceConfig {

    // Configurações adicionais de persistência podem ser adicionadas aqui
    // como configurações de cache, auditoria, etc.
}
