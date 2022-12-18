package ru.study.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.model.database.DayEntity;

@Repository
public interface DayRepository extends JpaRepository<DayEntity, Long> {

}
