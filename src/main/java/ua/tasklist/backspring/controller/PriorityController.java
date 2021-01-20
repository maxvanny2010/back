package ua.tasklist.backspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tasklist.backspring.entity.Priority;
import ua.tasklist.backspring.repo.PriorityRepo;

import java.util.List;

/**
 * PriorityController.
 *
 * @author legion
 * @version 5.0
 * @since 20.01.2021
 */
@RestController
@RequestMapping("/priority")
public class PriorityController {
    private final PriorityRepo priorityRepo;

    public PriorityController(final PriorityRepo priorityRepo) {
        this.priorityRepo = priorityRepo;
    }

    @GetMapping("/test")
    public List<Priority> test() {
        final var all = this.priorityRepo.findAll();
        System.out.println("list " + all);
        return all;
    }
}
