package ua.tasklist.backspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tasklist.backspring.entity.Category;
import ua.tasklist.backspring.repo.CategoryRepo;

import java.util.List;

/**
 * CategoryController.
 *
 * @author legion
 * @version 5.0
 * @since 20.01.2021
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryRepo categoryRepo;

    public CategoryController(final CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/test")
    public List<Category> test() {
        return this.categoryRepo.findAll();
    }
}
