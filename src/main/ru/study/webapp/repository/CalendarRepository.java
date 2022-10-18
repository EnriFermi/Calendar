package ru.study.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.study.webapp.database.CalendarDAO;

public interface CalendarRepository extends CrudRepository<CalendarDAO, Long> {

}