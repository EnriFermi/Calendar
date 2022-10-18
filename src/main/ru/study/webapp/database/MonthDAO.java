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

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "monthlist", schema = "calendarconfiguration", catalog = "")
public class MonthDAO {
    @Id
    @JsonIgnore
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
    private YearDAO yearDAO;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monthDAO")
    private List<DayWorkOutDAO> dayWorkOutList;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monthDAO")
    private List<DayWorkDAO> dayWorkList;
}
