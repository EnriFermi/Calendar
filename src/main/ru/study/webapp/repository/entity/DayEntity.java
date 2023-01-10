package ru.study.webapp.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity
@Table(name = "daylist", catalog = "",
        uniqueConstraints = {@UniqueConstraint(name = "test", columnNames = {"dayName", "calendarId"})})
public class DayEntity {
    public DayEntity(){}

    public DayEntity(Long id, String dayName, Boolean weekDayWorkOut, CalendarEntity calendarEntity){
        this.id = id;
        this.dayName = dayName;
        this.weekDayWorkOut = weekDayWorkOut;
        this.calendarEntity = calendarEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dayId")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendarId")
    private CalendarEntity calendarEntity;


    @EqualsAndHashCode.Include
    @Column(name = "dayName")
    private String dayName;

    @Column(name = "weekDayWorkOut")
    private Boolean weekDayWorkOut;
}