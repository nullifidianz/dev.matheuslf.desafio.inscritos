package dev.matheuslf.desafio.inscritos.domain.shared;

import org.springframework.data.jpa.domain.Specification;

/**
 * Classe utilitária para criar Specifications.
 * Implementa o padrão Specification do DDD.
 */
public class Specifications {

    private Specifications() {
        // Classe utilitária
    }

    /**
     * Cria uma specification que sempre retorna true (para casos de filtro
     * opcional)
     */
    public static <T> Specification<T> alwaysTrue() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    /**
     * Cria uma specification que sempre retorna false
     */
    public static <T> Specification<T> alwaysFalse() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.disjunction();
    }
}
