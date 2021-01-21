package ua.tasklist.backspring.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tasklist.backspring.entity.Task;
import ua.tasklist.backspring.repo.TaskRepo;
import ua.tasklist.backspring.search.TaskSearchValues;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * taskController.
 *
 * @author legion
 * @version 5.0
 * @since 20.01.2021
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskRepo taskRepo;

    public TaskController(final TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(this.taskRepo.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Task task) {
        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity<>("redundant parameter:ID must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity<>("missing parameter: TITLE must not be null", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(this.taskRepo.save(task));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Task task) {
        if (task.getId() == null || task.getId() == 0) {
            return new ResponseEntity<>("parameter: ID must not be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity<>("missing parameter: TITLE must not be null", HttpStatus.NOT_ACCEPTABLE);
        }
        this.taskRepo.save(task);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Task task = new Task();
        try {
            Optional<Task> tmp = this.taskRepo.findById(id);
            if (tmp.isPresent()) {
                task = tmp.get();
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("ID=" + id + " not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            this.taskRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("ID=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Task>> search(@RequestBody TaskSearchValues taskSearchValues) {
        final var title = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;
        final var completed = taskSearchValues.getCompleted() != null ? taskSearchValues.getCompleted() : null;
        final var priorityId = taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() : null;
        final var categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;
        return ResponseEntity.ok(this.taskRepo.findByParams(title, completed, priorityId, categoryId));
    }
}
