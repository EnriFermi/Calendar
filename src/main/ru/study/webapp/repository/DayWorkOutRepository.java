package ru.study.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.model.database.DayWorkOutDatabaseModel;

@Repository
public interface DayWorkOutRepository extends CrudRepository<DayWorkOutDatabaseModel, Long> {

}
