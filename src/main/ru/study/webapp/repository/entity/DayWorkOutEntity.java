package ru.study.webapp.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Table(name = "dayworkoutlist", /*schema = "calendarconfiguration",*/ catalog = "",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"dateOfWorkOutDay", "monthId"})})
public class DayWorkOutEntity {

    public DayWorkOutEntity(){}
    public DayWorkOutEntity(Long id, Integer dateOfWorkOutDay, MonthEntity monthEntity){
        this.id = id;
        this.dateOfWorkOutDay = dateOfWorkOutDay;
        this.monthEntity = monthEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dayWorkOutId")
    private Long id;

    @Column(name = "dateOfWorkOutDay")
    private Integer dateOfWorkOutDay;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "monthId")
    private MonthEntity monthEntity;
}
