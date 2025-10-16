package dev.matheuslf.desafio.inscritos.interfaces.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import dev.matheuslf.desafio.inscritos.domain.task.Task;
import dev.matheuslf.desafio.inscritos.application.TaskApplicationService;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskRequest;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskResponse;
import dev.matheuslf.desafio.inscritos.interfaces.dto.task.TaskStatusUpdateRequest;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskApplicationService taskApplicationService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        TaskResponse response = taskApplicationService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getTasksWithFilters(
            @RequestParam(required = false) Task.TaskStatus status,
            @RequestParam(required = false) Task.TaskPriority priority,
            @RequestParam(required = false) UUID projectId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort) {

        if (page != null || size != null || sort != null) {
            Pageable pageable = Pageable.ofSize(size != null ? size : 10)
                    .withPage(page != null ? page : 0);
            Page<TaskResponse> response = taskApplicationService.getTasksWithFilters(status,
                    priority != null ? priority.name() : null, projectId, pageable);
            return ResponseEntity.ok(response);
        } else {
            List<TaskResponse> response = taskApplicationService.getAllTasks();
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(
            @PathVariable UUID id,
            @Valid @RequestBody TaskStatusUpdateRequest request) {
        TaskResponse response = taskApplicationService.updateTaskStatus(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskApplicationService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable UUID id) {
        TaskResponse response = taskApplicationService.getTaskById(id);
        return ResponseEntity.ok(response);
    }
}
