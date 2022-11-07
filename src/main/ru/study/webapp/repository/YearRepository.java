package ru.study.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.model.database.YearDatabaseModel;

@Repository
//TODO найти разницу с JpaRepository
public interface YearRepository extends CrudRepository<YearDatabaseModel, Long> {

}
