package ua.tasklist.backspring.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tasklist.backspring.entity.Priority;
import ua.tasklist.backspring.search.PrioritySearchValue;
import ua.tasklist.backspring.services.PriorityService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * PriorityController.
 *
 * @author legion
 * @version 5.0
 * @since 20.01.2021
 */
@RestController
@RequestMapping("/priority")
@CrossOrigin(origins = "http://localhost:4200")
public class PriorityController {
    private final PriorityService service;

    public PriorityController(final PriorityService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Priority>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Priority priority) {
        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity<>("redundant parameter:ID must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity<>("missing parameter: TITLE must not be null", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(this.service.add(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Priority priority) {
        if (priority.getId() == null || priority.getId() == 0) {
            return new ResponseEntity<>("parameter:ID must not be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity<>("missing parameter: TITLE must not be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity<>("missing parameter: COLOR must not be null", HttpStatus.NOT_ACCEPTABLE);
        }
        this.service.update(priority);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Priority priority = new Priority();
        try {
            Optional<Priority> tmp = this.service.findById(id);
            if (tmp.isPresent()) {
                priority = tmp.get();
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("ID=" + id + " not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(priority);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            this.service.delete(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("ID=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValue prioritySearchValue) {
        return ResponseEntity.ok(this.service.search(prioritySearchValue));
    }
}
