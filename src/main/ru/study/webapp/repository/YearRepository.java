package ru.study.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.webapp.repository.entity.YearEntity;

@Repository
public interface YearRepository extends JpaRepository<YearEntity, Long> {

}
