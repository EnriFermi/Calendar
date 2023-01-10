package ru.study.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.repository.entity.DayEntity;

@Repository
public interface DayRepository extends JpaRepository<DayEntity, Long> {

}
