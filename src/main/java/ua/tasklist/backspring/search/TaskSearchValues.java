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
    private String title;
    private Long priorityId;
    private Long categoryId;
    private Integer completed;

    private Integer pageNumber;
    private Integer pageSize;

    private String sortColumn;
    private String sortDirection;
    //names must be the same names on frontend
}
