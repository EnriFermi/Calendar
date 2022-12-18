package ru.study.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.model.database.CalendarEntity;
import ru.study.webapp.repository.custom.CalendarRepositoryCustom;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, Long>, CalendarRepositoryCustom {

}