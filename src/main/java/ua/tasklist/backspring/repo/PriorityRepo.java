package ua.tasklist.backspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.tasklist.backspring.entity.Priority;

import java.util.List;

/**
 * PriorityRepo.
 *
 * @author legion
 * @version 5.0
 * @since 20.01.2021
 */
@Repository
public interface PriorityRepo extends JpaRepository<Priority, Long> {
    @Query("select p from Priority p where"
            + "(:title is null or :title='' or lower(p.title) like lower(concat('%',:title,'%')))"
            + " order by p.title asc"
    )
    List<Priority> findByTitle(@Param("title") String title);

    List<Priority> findAllByOrderByIdAsc();
}
