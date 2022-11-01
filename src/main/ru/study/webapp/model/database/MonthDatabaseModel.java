package ru.study.webapp.model.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "monthlist", /*schema = "calendarconfiguration",*/ catalog = "")
public class MonthDatabaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monthId")
    private Long id;


    @EqualsAndHashCode.Include
    @Column(name = "monthName")
    private String name;


    @Column(name = "dayCount")
    private Integer dayCount;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yearId")
    private YearDatabaseModel yearDatabaseModel;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monthDatabaseModel")
    private List<DayWorkOutDatabaseModel> dayWorkOutList;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monthDatabaseModel")
    private List<DayWorkDatabaseModel> dayWorkList;
}
