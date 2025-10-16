package dev.matheuslf.desafio.inscritos.interfaces.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.matheuslf.desafio.inscritos.application.ProjectApplicationService;

// TODO: Corrigir problemas com ApplicationContext e JPA nos testes
// TODO: Resolver erro "JPA metamodel must not be empty"
// TODO: Configurar testes para não carregar configurações JPA desnecessárias
@WebMvcTest(controllers = ProjectController.class, excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class,
        org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectApplicationService projectApplicationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createProject_ShouldReturnCreatedProject_WhenValidRequest() throws Exception {
        // TODO: Implementar teste de criação de projeto
        // TODO: Corrigir problemas com ApplicationContext e JPA nos testes
        // TODO: Resolver erro "JPA metamodel must not be empty"
        // TODO: Configurar testes para não carregar configurações JPA desnecessárias
    }

    @Test
    void getAllProjects_ShouldReturnProjects_WhenCalled() throws Exception {
        // TODO: Implementar teste de listagem de projetos
        // TODO: Corrigir problemas com ApplicationContext e JPA nos testes
        // TODO: Resolver erro "JPA metamodel must not be empty"
        // TODO: Configurar testes para não carregar configurações JPA desnecessárias
    }

    @Test
    void getProjectById_ShouldReturnProject_WhenProjectExists() throws Exception {
        // TODO: Implementar teste de busca de projeto por ID
        // TODO: Corrigir problemas com ApplicationContext e JPA nos testes
        // TODO: Resolver erro "JPA metamodel must not be empty"
        // TODO: Configurar testes para não carregar configurações JPA desnecessárias
    }
}
