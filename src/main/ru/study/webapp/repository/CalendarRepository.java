package ru.study.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.study.webapp.model.database.CalendarDatabaseModel;

public interface CalendarRepository extends JpaRepository<CalendarDatabaseModel, Long> {

}