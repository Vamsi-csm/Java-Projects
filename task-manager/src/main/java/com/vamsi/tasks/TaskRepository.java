package com.vamsi.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Task.Status status);
    List<Task> findByTitleContainingIgnoreCase(String q);
}
