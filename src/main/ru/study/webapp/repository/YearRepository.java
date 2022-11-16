package ru.study.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.model.database.YearDatabaseModel;

@Repository
//TODO найти разницу с JpaRepository DONE
public interface YearRepository extends JpaRepository<YearDatabaseModel, Long> {

}
