package dev.matheuslf.desafio.inscritos.domain;

import dev.matheuslf.desafio.inscritos.domain.project.Project;
import dev.matheuslf.desafio.inscritos.interfaces.dto.project.ProjectRequest;
import dev.matheuslf.desafio.inscritos.interfaces.dto.project.ProjectResponse;

public class ProjectMapper {
    public static Project toEntity(ProjectRequest request) {
        Project project = new Project();
        project.setName(request.name());
        project.setDescription(request.description());
        project.setStartDate(request.startDate());
        project.setEndDate(request.endDate());
        return project;
    }

    public static ProjectResponse toResponse(Project project) {
        ProjectResponse projectResponse = new ProjectResponse(project.getId(), project.getName(),
                project.getDescription(), project.getStartDate(), project.getEndDate());
        project.setUpdatedAt(project.getUpdatedAt());
        project.setDeletedAt(project.getDeletedAt());
        return projectResponse;
    }

}
