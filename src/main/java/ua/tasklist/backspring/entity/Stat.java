package ua.tasklist.backspring.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Stat.
 *
 * @author legion
 * @version 5.0
 * @since 20.01.2021
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Stat {
    private Long id;
    private Long completedTotal;
    private Long uncompletedTotal;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "completed_total")
    public Long getCompletedTotal() {
        return completedTotal;
    }

    public void setCompletedTotal(final Long completedTotal) {
        this.completedTotal = completedTotal;
    }

    @Basic
    @Column(name = "uncompleted_total")
    public Long getUncompletedTotal() {
        return uncompletedTotal;
    }

    public void setUncompletedTotal(final Long uncompletedTotal) {
        this.uncompletedTotal = uncompletedTotal;
    }
}
