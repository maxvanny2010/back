package ua.tasklist.backspring.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.tasklist.backspring.entity.Task;
import ua.tasklist.backspring.repo.TaskRepo;
import ua.tasklist.backspring.search.TaskSearchValues;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * TaskService.
 *
 * @author legion
 * @version 5.0
 * @since 21.01.2021
 */
@Service
@Transactional
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(final TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<Task> findAll() {
        return this.taskRepo.findAll();
    }

    public Task add(final Task task) {
        return this.taskRepo.save(task);
    }

    public Task update(final Task task) {
        return this.taskRepo.save(task);
    }

    public Optional<Task> findById(final Long id) {
        return this.taskRepo.findById(id);
    }

    public void delete(final Long id) {
        this.taskRepo.deleteById(id);
    }

    public Page<Task> search(final TaskSearchValues taskSearchValues) {
        final var title = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;
        final var completed = taskSearchValues.getCompleted() != null ? taskSearchValues.getCompleted() : null;
        final var priorityId = taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() : null;
        final var categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;
        final var sortColumn = taskSearchValues.getSortColumn() != null ? taskSearchValues.getSortColumn() : null;
        final var sortDirection = taskSearchValues.getSortDirection() != null ? taskSearchValues.getSortDirection() : null;
        final var pageNumber = taskSearchValues.getPageNumber();
        final var pageSize = taskSearchValues.getPageSize();
        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0
                || Objects.requireNonNull(sortDirection).trim().equals("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortColumn);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return this.taskRepo.findByParams(title, completed, priorityId, categoryId, pageRequest);
    }
}
