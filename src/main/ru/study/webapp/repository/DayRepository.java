package ru.study.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.model.database.DayDatabaseModel;
@Repository
public interface DayRepository extends CrudRepository<DayDatabaseModel, Long> {

}
