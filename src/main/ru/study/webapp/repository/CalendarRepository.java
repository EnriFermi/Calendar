package ru.study.webapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.repository.entity.CalendarEntity;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {
    //TODO динамически строить запрос в коде, например используя https://www.baeldung.com/hibernate-criteria-queries
    @Transactional
    @Query("""
            select c from CalendarEntity c where (:monthName is null or (c.id in (select y.calendarEntity.id from YearEntity y where y.id in
                                                              (select m.yearEntity.id from MonthEntity m where m.name = :monthName)))) and 
                                                              (:beginningYear is null or c.beginningYear = :beginningYear) and
                                                              (:endYear is null or c.endYear = :endYear)""")
    Page<CalendarEntity> findByYearList_MonthList_NameLikeAndBeginningYearAndEndYear(
            @Nullable @Param("monthName") String search,
            @Nullable @Param("beginningYear") Integer beginningYear,
            @Nullable @Param("endYear") Integer endYear, Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = """
            update CalendarEntity c set c.version = c.version + 1, c.beginningYear = :beginningYear,
                                        c.endYear = :endYear where c.id = :id and c.version  = :version""")
    int updateByYearList_MonthList_NameLikeAndBeginningYearAndEndYear(
            @Nullable @Param("beginningYear") Integer beginningYear,
            @Nullable @Param("endYear") Integer endYear,
            @NonNull @Param("id") Long id,
            @NonNull @Param("version") Long version);

}