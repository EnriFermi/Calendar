package ru.study.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.study.webapp.model.database.CalendarDatabaseModel;

public interface CalendarRepository extends CrudRepository<CalendarDatabaseModel, Long> {

}