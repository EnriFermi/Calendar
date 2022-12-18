package ru.study.webapp.repository.custom.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.study.webapp.controller.requests.CalendarRequest;
import ru.study.webapp.controller.requests.CalendarSearchFilter;
import ru.study.webapp.controller.requests.CalendarSearchFilterEnum;
import ru.study.webapp.model.database.CalendarEntity;
import ru.study.webapp.model.database.MonthEntity;
import ru.study.webapp.model.database.YearEntity;
import ru.study.webapp.repository.custom.CalendarRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
public class CalendarRepositoryCustomImpl implements CalendarRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Page<CalendarEntity> findAllParametrised(CalendarRequest request, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CalendarEntity> query = cb.createQuery(CalendarEntity.class);
        Root<CalendarEntity> calendarEntityRoot = query.from(CalendarEntity.class);
        Path<String> parameterPath;
        List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
        List<CalendarSearchFilter> searchFilterList = request.getSearchFilterList();
        for (CalendarSearchFilter filter : searchFilterList) {

            if (filter.getFilterType().equals(CalendarSearchFilterEnum.IS_BEGINNING_YEAR) ||
                    filter.getFilterType().equals(CalendarSearchFilterEnum.IS_END_YEAR)) {
                System.out.println(filter.getFilterType().getDtFieldName());
                parameterPath = calendarEntityRoot.get(filter.getFilterType().getDtFieldName().get(0));
                predicates.add(cb.equal(parameterPath, Integer.parseInt((String) filter.getFilterBody())));
            }
            if (filter.getFilterType().equals(CalendarSearchFilterEnum.CONTAINING_MONTH)){
                Subquery<MonthEntity> monthEntitySubquery = query.subquery(MonthEntity.class);
                Root<MonthEntity> monthEntityRoot = monthEntitySubquery.from(MonthEntity.class);
                parameterPath = monthEntityRoot.get(filter.getFilterType().getDtFieldName().get(0));
                monthEntitySubquery.select(monthEntityRoot)
                        .distinct(true)
                        .where(cb.equal(parameterPath,  Integer.parseInt((String) filter.getFilterBody())));
                Subquery<YearEntity> yearEntitySubquery = query.subquery(YearEntity.class);
                Root<YearEntity> yearEntityRoot = yearEntitySubquery.from(YearEntity.class);
                yearEntitySubquery.select(yearEntityRoot)
                        .where(cb.in(yearEntityRoot.get(filter.getFilterType().getDtFieldName().get(1)))
                                .value(monthEntitySubquery));
                predicates.add(cb.in(calendarEntityRoot.get(filter.getFilterType()
                        .getDtFieldName().get(2))).value(yearEntitySubquery));
            }

        }
        query.select(calendarEntityRoot)
                .where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        List<CalendarEntity> list = entityManager.createQuery(query).getResultList();
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        Page<CalendarEntity> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
        return page;
    }
}
