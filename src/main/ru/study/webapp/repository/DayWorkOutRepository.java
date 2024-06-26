package ru.study.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.repository.entity.DayWorkOutEntity;

@Repository
public interface DayWorkOutRepository extends JpaRepository<DayWorkOutEntity, Long> {

}
