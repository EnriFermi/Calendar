package ru.study.webapp.model.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Table(name = "dayworklist", /*schema = "calendarconfiguration",*/ catalog = "",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"dateOfWorkDay", "monthId"})})
public class DayWorkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dayWorkId")
    private Long id;

    @Column(name = "dateOfWorkDay")
    private Integer dateOfWorkDay;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "monthId")
    private MonthEntity monthEntity;
}
