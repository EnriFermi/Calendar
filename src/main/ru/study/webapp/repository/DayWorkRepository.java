package ru.study.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.model.database.DayWorkDatabaseModel;

@Repository
public interface DayWorkRepository extends CrudRepository<DayWorkDatabaseModel, Long> {

}
