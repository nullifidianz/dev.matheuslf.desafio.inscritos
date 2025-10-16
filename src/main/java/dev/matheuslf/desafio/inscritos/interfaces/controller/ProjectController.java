package dev.matheuslf.desafio.inscritos.interfaces.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import dev.matheuslf.desafio.inscritos.application.ProjectApplicationService;
import dev.matheuslf.desafio.inscritos.interfaces.dto.project.ProjectRequest;
import dev.matheuslf.desafio.inscritos.interfaces.dto.project.ProjectResponse;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectApplicationService projectApplicationService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request) {
        ProjectResponse response = projectApplicationService.createProject(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllProjects(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort) {

        if (page != null || size != null || sort != null) {
            Pageable pageable = Pageable.ofSize(size != null ? size : 10)
                    .withPage(page != null ? page : 0);
            Page<ProjectResponse> response = projectApplicationService.getAllProjectsPaginated(pageable);
            return ResponseEntity.ok(response);
        } else {
            List<ProjectResponse> response = projectApplicationService.getAllProjects();
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable UUID id) {
        ProjectResponse response = projectApplicationService.getProjectById(id);
        return ResponseEntity.ok(response);
    }
}
