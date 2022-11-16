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
public class CalendarDatabaseModel {
    public CalendarDatabaseModel(Long id){
        this.id = id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendarId")
    @Setter(AccessLevel.PUBLIC)
    private Long id;

    @Setter(AccessLevel.PUBLIC)
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendarDatabaseModel")
    private List<YearDatabaseModel> yearList;

    @Setter(AccessLevel.PUBLIC)
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendarDatabaseModel")
    private List<DayDatabaseModel> dayList;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "anchorWeekDayKey")
    @Setter(AccessLevel.PUBLIC)
    private DayDatabaseModel anchorWeekDay;


    @Column(name = "beginningYear")
    @Setter(AccessLevel.PUBLIC)
    private Integer beginningYear;


    @Column(name = "endYear")
    @Setter(AccessLevel.PUBLIC)
    private Integer endYear;

    public CalendarDatabaseModel() {}
}
