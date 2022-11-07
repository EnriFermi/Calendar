package ru.study.webapp.model.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "yearlist", /*schema = "calendarconfiguration",*/ catalog = "")
@Valid
public class YearDatabaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "yearId")
    //TODO прочитать про @Valid
    @NonNull
    private Long id;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendarId")
    private CalendarDatabaseModel calendarDatabaseModel;

    @JsonManagedReference
    @EqualsAndHashCode.Include()
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "yearDatabaseModel")
    private List<MonthDatabaseModel> monthList;

}
