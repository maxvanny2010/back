package ua.tasklist.backspring.services;

import org.springframework.stereotype.Service;
import ua.tasklist.backspring.entity.Category;
import ua.tasklist.backspring.repo.CategoryRepo;
import ua.tasklist.backspring.search.CategorySearchValues;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * CategoryService.
 *
 * @author legion
 * @version 5.0
 * @since 21.01.2021
 */
@Service
@Transactional
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryService(final CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> findAll() {
        return this.categoryRepo.findAllByOrderByTitleAsc();
    }

    public Category add(final Category category) {
        return this.categoryRepo.save(category);
    }

    public Category update(final Category category) {
        return this.categoryRepo.save(category);
    }

    public Optional<Category> findById(final Long id) {
        return this.categoryRepo.findById(id);
    }

    public void delete(final Long id) {
        this.categoryRepo.deleteById(id);
    }

    public List<Category> search(final CategorySearchValues categorySearchValues) {
        // if null to show all categories
        return this.categoryRepo.findByTitle(categorySearchValues.getText());
    }
}
