package ua.tasklist.backspring.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.tasklist.backspring.entity.Task;

import java.util.List;

/**
 * TaskRepo.
 *
 * @author legion
 * @version 5.0
 * @since 21.01.2021
 */
public interface TaskRepo extends JpaRepository<Task, Long> {
    @Query("SELECT t from Task t where "
            + "(:title is null or :title='' or lower(t.title) like lower(concat('%',:title,'%'))) "
            + "order by t.title asc"
    )
    List<Task> findByTitle(@Param("title") String title);

    @Query("SELECT t from Task t where "
            + "(:title is null or :title='' or lower(t.title) like lower(concat('%', :title,'%'))) and "
            + "(:completed is null or t.completed=:completed) and "
            + "(:priorityId is null or t.priority.id=:priorityId) and "
            + "(:categoryId is null or t.category.id=:categoryId)"
    )
    Page<Task> findByParams(
            @Param("title") String title,
            @Param("completed") Integer completed,
            @Param("priorityId") Long priorityId,
            @Param("categoryId") Long categoryId,
            Pageable pageable
    );

}
