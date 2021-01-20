package ua.tasklist.backspring.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.tasklist.backspring.entity.Stat;

/**
 * StatRepo.
 *
 * @author legion
 * @version 5.0
 * @since 20.01.2021
 */
@Repository
public interface StatRepo extends CrudRepository<Stat, Long> {
}
