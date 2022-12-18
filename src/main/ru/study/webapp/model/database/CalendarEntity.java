package ru.study.webapp.model.database;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter(AccessLevel.PUBLIC)
@Entity
@Table(name = "calendarlist", /*schema = "calendarconfiguration",*/ catalog = "")
@DynamicUpdate
//TODO rename to *Entity
//TODO добавить поле версии, не позволять параллельное изменение
public class CalendarEntity {
    public CalendarEntity(Long id){
        this.id = id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendarId")
    @Setter(AccessLevel.PUBLIC)
    private Long id;

    @Setter(AccessLevel.PUBLIC)
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendarEntity")
    private List<YearEntity> yearList;

    @Setter(AccessLevel.PUBLIC)
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendarEntity")
    private List<DayEntity> dayList;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "anchorWeekDayKey")
    @Setter(AccessLevel.PUBLIC)
    private DayEntity anchorWeekDay;


    @Column(name = "beginningYear")
    @Setter(AccessLevel.PUBLIC)
    private Integer beginningYear;


    @Column(name = "endYear")
    @Setter(AccessLevel.PUBLIC)
    private Integer endYear;

    public CalendarEntity() {}
}
