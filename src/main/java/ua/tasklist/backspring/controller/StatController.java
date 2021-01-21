package ua.tasklist.backspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tasklist.backspring.entity.Stat;
import ua.tasklist.backspring.repo.StatRepo;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * StatController.
 *
 * @author legion
 * @version 5.0
 * @since 20.01.2021
 */
@RestController
public class StatController {
    private final StatRepo statRepo;

    public StatController(final StatRepo priorityRepo) {
        this.statRepo = priorityRepo;
    }

    @GetMapping("/stat")
    public ResponseEntity<?> findById() {
        final Long defaultId = 1L;
        Stat stat = new Stat();
        try {
            Optional<Stat> tmp = this.statRepo.findById(defaultId);
            if (tmp.isPresent()) {
                stat = tmp.get();
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("ID=" + defaultId + " not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(stat);
    }
}