package com.vamsi.tasks;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Task {
    public enum Status { TODO, IN_PROGRESS, DONE }
    public enum Priority { LOW, MEDIUM, HIGH }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 120)
    private String title;
    @Size(max = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status = Status.TODO;
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;
    private LocalDate dueDate;

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}
