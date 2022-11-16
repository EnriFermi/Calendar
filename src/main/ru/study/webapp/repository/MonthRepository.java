package ru.study.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.model.database.MonthDatabaseModel;

@Repository
public interface MonthRepository extends JpaRepository<MonthDatabaseModel, Long> {

}