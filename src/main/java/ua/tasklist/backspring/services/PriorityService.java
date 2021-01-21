package ua.tasklist.backspring.services;

import org.springframework.stereotype.Service;
import ua.tasklist.backspring.entity.Priority;
import ua.tasklist.backspring.repo.PriorityRepo;
import ua.tasklist.backspring.search.PrioritySearchValue;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * PriiorityService.
 *
 * @author legion
 * @version 5.0
 * @since 21.01.2021
 */
@Service
@Transactional
public class PriorityService {
    private final PriorityRepo priorityRepo;

    public PriorityService(final PriorityRepo priorityRepo) {
        this.priorityRepo = priorityRepo;
    }

    public List<Priority> findAll() {
        return this.priorityRepo.findAllByOrderByIdAsc();
    }

    public Priority add(Priority priority) {
        return this.priorityRepo.save(priority);
    }

    public Priority update(Priority priority) {
        return this.priorityRepo.save(priority);
    }

    public Optional<Priority> findById(final Long id) {
        return this.priorityRepo.findById(id);
    }

    public void delete(final Long id) {
        this.priorityRepo.deleteById(id);
    }

    public List<Priority> search(PrioritySearchValue prioritySearchValue) {
        return this.priorityRepo.findByTitle(prioritySearchValue.getText());
    }
}
