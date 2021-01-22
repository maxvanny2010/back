package ua.tasklist.backspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tasklist.backspring.entity.Stat;
import ua.tasklist.backspring.services.StatService;

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
@CrossOrigin(origins = "http://localhost:4200")
public class StatController {
    private final StatService service;

    public StatController(final StatService service) {
        this.service = service;
    }

    @GetMapping("/stat")
    public ResponseEntity<?> findById() {
        final Long defaultId = 1L;
        Stat stat = new Stat();
        try {
            Optional<Stat> tmp = this.service.findById(defaultId);
            if (tmp.isPresent()) {
                stat = tmp.get();
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("ID=" + defaultId + " not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(stat);
    }
}
