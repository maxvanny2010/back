package ua.tasklist.backspring.services;

import org.springframework.stereotype.Service;
import ua.tasklist.backspring.entity.Stat;
import ua.tasklist.backspring.repo.StatRepo;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Stat.
 *
 * @author legion
 * @version 5.0
 * @since 21.01.2021
 */
@Service
@Transactional
public class StatService {
    private final StatRepo statRepo;

    public StatService(final StatRepo priorityRepo) {
        this.statRepo = priorityRepo;
    }

    public Optional<Stat> findById(final Long defaultId) {
        return this.statRepo.findById(defaultId);
    }
}
