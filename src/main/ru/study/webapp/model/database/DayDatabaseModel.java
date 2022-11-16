package ru.study.webapp.model.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity
@UniqueElements()
@Table(name = "daylist", /*schema = "calendarconfiguration",*/ catalog = "",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"dayName", "calendarId"})})
public class DayDatabaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dayId")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendarId")
    private CalendarDatabaseModel calendarDatabaseModel;


    @EqualsAndHashCode.Include
    @Column(name = "dayName")
    private String dayName;

    @Column(name = "weekDayWorkOut")
    private Boolean weekDayWorkOut;
}