package com.vamsi.tasks;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskRepository repo;

    public TaskController(TaskRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Task> list(@RequestParam(required = false) Task.Status status,
                           @RequestParam(required = false) String q) {
        if (status != null) return repo.findByStatus(status);
        if (q != null && !q.isBlank()) return repo.findByTitleContainingIgnoreCase(q);
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody Task task) { return repo.save(task); }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody Task in) {
        return repo.findById(id).map(t -> {
            t.setTitle(in.getTitle());
            t.setDescription(in.getDescription());
            t.setStatus(in.getStatus());
            t.setPriority(in.getPriority());
            t.setDueDate(in.getDueDate());
            return ResponseEntity.ok(repo.save(t));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
