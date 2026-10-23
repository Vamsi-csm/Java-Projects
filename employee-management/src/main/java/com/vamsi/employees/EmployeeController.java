package com.vamsi.employees;

import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeRepository repo;
    public EmployeeController(EmployeeRepository repo) { this.repo = repo; }

    @GetMapping
    public Page<Employee> list(@RequestParam(defaultValue = "") String q,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        return repo.search(q, PageRequest.of(page, Math.min(size, 100), Sort.by("name")));
    }

    @GetMapping("/stats")
    public List<EmployeeRepository.DepartmentCount> stats() { return repo.countByDepartment(); }

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@Valid @RequestBody Employee e) {
        if (repo.existsByEmail(e.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        return repo.save(e);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody Employee in) {
        Employee e = get(id);
        e.setName(in.getName()); e.setEmail(in.getEmail()); e.setDepartment(in.getDepartment());
        e.setRole(in.getRole()); e.setSalary(in.getSalary());
        return repo.save(e);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { repo.delete(get(id)); }
}
