package ru.study.webapp.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "yearlist", schema = "calendarconfiguration", catalog = "")
public class YearDAO {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "yearId")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendarId")
    private CalendarDAO calendarDAO;

    @JsonManagedReference
    @EqualsAndHashCode.Include()
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "yearDAO")
    private List<MonthDAO> monthList;

}
