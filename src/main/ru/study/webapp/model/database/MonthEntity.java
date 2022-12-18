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
@Table(name = "monthlist", /*schema = "calendarconfiguration",*/ catalog = "",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"monthName", "yearId"})})
public class MonthEntity {
    public MonthEntity() {}
    public MonthEntity(Long id){
        this.id = id;
    }
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
    private YearEntity yearEntity;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monthEntity")
    private List<DayWorkOutEntity> dayWorkOutList;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monthEntity")
    private List<DayWorkEntity> dayWorkList;
}
