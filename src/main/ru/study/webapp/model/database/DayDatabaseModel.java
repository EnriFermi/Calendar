package ru.study.webapp.model.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity
@Table(name = "daylist", schema = "calendarconfiguration", catalog = "")
public class DayDatabaseModel {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dayId")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "calendarId")
    private CalendarDatabaseModel calendarDatabaseModel;


    @EqualsAndHashCode.Include
    @Column(name = "dayName")
    private String dayName;

    @Column(name = "weekDayWorkOut")
    private Boolean weekDayWorkOut;
}