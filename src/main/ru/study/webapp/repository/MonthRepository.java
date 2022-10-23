package ru.study.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.model.database.MonthDatabaseModel;

@Repository
public interface MonthRepository extends CrudRepository<MonthDatabaseModel, Long> {

}