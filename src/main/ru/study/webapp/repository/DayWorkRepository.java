package ru.study.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.repository.entity.DayWorkEntity;

@Repository
public interface DayWorkRepository extends JpaRepository<DayWorkEntity, Long> {

}
