package ua.tasklist.backspring.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TaskSearchValues.
 *
 * @author legion
 * @version 5.0
 * @since 21.01.2021
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskSearchValues {
    String title;
    Long priorityId;
    Long categoryId;
    Integer completed;
}
