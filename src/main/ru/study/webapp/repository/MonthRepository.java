package ru.study.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.model.database.MonthEntity;

@Repository
public interface MonthRepository extends JpaRepository<MonthEntity, Long> {

}