package dev.matheuslf.desafio.inscritos.application;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import dev.matheuslf.desafio.inscritos.domain.project.ProjectService;
import dev.matheuslf.desafio.inscritos.interfaces.dto.project.ProjectRequest;
import dev.matheuslf.desafio.inscritos.interfaces.dto.project.ProjectResponse;

/**
 * Application Service responsável por orquestrar casos de uso relacionados a
 * projetos.
 * Esta camada coordena entre os serviços de domínio e a infraestrutura.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ProjectApplicationService {

    private final ProjectService projectService;

    /**
     * Caso de uso: Criar um novo projeto
     */
    public ProjectResponse createProject(ProjectRequest request) {
        return projectService.createProject(request);
    }

    /**
     * Caso de uso: Listar todos os projetos com paginação
     */
    @Transactional(readOnly = true)
    public Page<ProjectResponse> getAllProjectsPaginated(Pageable pageable) {
        return projectService.getAllProjects(pageable);
    }

    /**
     * Caso de uso: Listar todos os projetos sem paginação
     */
    @Transactional(readOnly = true)
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects();
    }

    /**
     * Caso de uso: Buscar projeto por ID
     */
    @Transactional(readOnly = true)
    public ProjectResponse getProjectById(UUID id) {
        return projectService.getProjectById(id);
    }

    /**
     * Caso de uso: Verificar se projeto existe
     */
    @Transactional(readOnly = true)
    public boolean projectExists(UUID id) {
        return projectService.existsById(id);
    }
}
