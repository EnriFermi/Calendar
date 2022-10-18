package ru.study.webapp.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Table(name = "dayworkoutlist", schema = "calendarconfiguration", catalog = "")
public class DayWorkOutDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dayWorkOutId")
    private Long id;

    @Column(name = "dateOfWorkOutDay")
    private Integer dateOfWorkOutDay;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "monthId")
    private MonthDAO monthDAO;
}
