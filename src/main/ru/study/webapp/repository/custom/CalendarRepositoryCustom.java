package ru.study.webapp.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.study.webapp.controller.requests.CalendarRequest;
import ru.study.webapp.model.database.CalendarEntity;

@Repository
public interface CalendarRepositoryCustom {
    public Page<CalendarEntity> findAllParametrised(CalendarRequest request,
                                                    Pageable pageable);
}
