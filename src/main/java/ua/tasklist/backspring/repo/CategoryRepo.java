package ua.tasklist.backspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.tasklist.backspring.entity.Category;

import java.util.List;

/**
 * CategoryRepo.
 *
 * @author legion
 * @version 5.0
 * @since 20.01.2021
 */
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderByTitleAsc();
}
