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
import ua.tasklist.backspring.entity.Category;
import ua.tasklist.backspring.search.CategorySearchValues;
import ua.tasklist.backspring.services.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * CategoryController.
 *
 * @author legion
 * @version 5.0
 * @since 20.01.2021
 */
@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(final CategoryService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Category category) {
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity<>("redundant parameter:ID must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity<>("missing parameter: TITLE must not be null", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(this.service.add(category));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Category category) {
        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity<>("parameter: ID must not be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity<>("missing parameter: TITLE must not be null", HttpStatus.NOT_ACCEPTABLE);
        }
        this.service.update(category);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Category category = new Category();
        try {
            Optional<Category> tmp = this.service.findById(id);
            if (tmp.isPresent()) {
                category = tmp.get();
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("ID=" + id + " not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(category);
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
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues) {
        // if null to show all categories
        return ResponseEntity.ok(this.service.search(categorySearchValues));
    }
}
