package ru.study.webapp.model.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Table(name = "dayworkoutlist", schema = "calendarconfiguration", catalog = "")
public class DayWorkOutDatabaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dayWorkOutId")
    private Long id;

    @Column(name = "dateOfWorkOutDay")
    private Integer dateOfWorkOutDay;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "monthId")
    private MonthDatabaseModel monthDatabaseModel;
}
