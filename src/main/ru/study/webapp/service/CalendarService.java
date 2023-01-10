package ru.study.webapp.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.controller.dto.*;
import ru.study.webapp.controller.requests.CalendarSearchFilterEnum;
import ru.study.webapp.exceptions.NotFoundException;
import ru.study.webapp.model.database.*;
import ru.study.webapp.model.mappers.DatabaseDTOMapper;
import ru.study.webapp.repository.CalendarRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Service

public class CalendarService {
    private final CalendarRepository repository;
    private final DatabaseDTOMapper mapper = Mappers.getMapper(DatabaseDTOMapper.class);

    public CalendarService(CalendarRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<CalendarControllerDTO> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::calendarEntityToCalendarControllerDTO).toList();
    }

    @Transactional
    public Page<CalendarControllerDTO> getOnePage(Pageable page, Map<String, String> filterMap) {
        filterMap.getOrDefault(CalendarSearchFilterEnum.CONTAINING_MONTH.getJsonFieldName(), null);
        Page<CalendarEntity> res = repository
                .findByYearList_MonthList_NameLikeAndBeginningYearAndEndYear(
                        filterMap.getOrDefault(CalendarSearchFilterEnum.CONTAINING_MONTH.getJsonFieldName(), null),
                        NumberUtils.createInteger(filterMap.getOrDefault(CalendarSearchFilterEnum.IS_BEGINNING_YEAR.getJsonFieldName(), null)),
                        NumberUtils.createInteger(filterMap.getOrDefault(CalendarSearchFilterEnum.IS_END_YEAR.getJsonFieldName(), null)),
                        page);
        //TODO не ругаться. а вернуть пустой список DONE
        List<CalendarControllerDTO> list = res.stream()
                .map(mapper::calendarEntityToCalendarControllerDTO).toList();
        return new PageImpl<>(list
                , page, list.size());
    }

    @Transactional
    public CalendarControllerDTO getOne(Long id) {
        return mapper.calendarEntityToCalendarControllerDTO(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(CalendarControllerDTO.class, id)));
    }

    @Transactional
    public CalendarControllerDTO addOne(CalendarControllerDTO calendarControllerDTO) {
        return mapper.calendarEntityToCalendarControllerDTO(
                repository.save(mapper.calendarControllerDTOToCalendarEntity(calendarControllerDTO)));
    }

    @Transactional
    public CalendarControllerDTO setAnchorDay(Long calendarId, Long dayId) {
        return mapper.calendarEntityToCalendarControllerDTO(repository.findById(calendarId).map(calendar -> {
            for (DayEntity day : calendar.getDayList()) {
                if (day.getId().equals(dayId)) {
                    calendar.setAnchorWeekDay(day);
                    return repository.save(calendar);
                }
            }
            throw new NotFoundException(DayControllerDTO.class, calendarId);
        }).orElseThrow(() -> new NotFoundException(CalendarControllerDTO.class, calendarId))); //TODO можно anchorDayError
    }

    @Transactional
    public CalendarControllerDTO updateOne(CalendarControllerDTO calendarControllerDTO, Long id) {
        Integer count = repository.updateByYearList_MonthList_NameLikeAndBeginningYearAndEndYear(
                calendarControllerDTO.getBeginningYear(), calendarControllerDTO.getEndYear(), id,
                calendarControllerDTO.getVersion());
        if (count == 0) {
            throw new NotFoundException(CalendarControllerDTO.class, id);
        }
        return calendarControllerDTO;
        //TODO если не нашли по ИД, должна быть ошибка DONE
    }

    @Transactional
    public Long deleteOne(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(CalendarControllerDTO.class, id);
        } else {
            repository.deleteById(id);
            return id;
        }
    }

    @Transactional
    public DayControllerDTO getAnchorDay(Long id) {
        return mapper.dayEntityToDayControllerDTO(repository.findById(id).orElseThrow().getAnchorWeekDay());
    }

    @Transactional
    public CalendarControllerDTO addOneNested(CalendarControllerDTO calendarControllerDTO,
                                              List<YearControllerDTO> yearControllerDTOList,
                                              List<MonthControllerDTO> monthControllerDTOList,
                                              List<DayControllerDTO> dayControllerDTOList,
                                              List<DayWorkControllerDTO> dayWorkControllerDTOList,
                                              List<DayWorkOutControllerDTO> dayWorkOutControllerDTOList,
                                              Integer anchorDayId) {
        CalendarEntity calendarEntity = new CalendarEntity(calendarControllerDTO.getId(),
                calendarControllerDTO.getVersion(),
                calendarControllerDTO.getBeginningYear(),
                calendarControllerDTO.getEndYear());
        yearControllerDTOList.forEach(year -> {
            YearEntity yearEntity = new YearEntity(year.getId(), calendarEntity);
            monthControllerDTOList.stream().filter(month -> month.getYearControllerDTOId().equals(year.getId()))
                    .forEach(month -> {
                        System.out.println("d");
                        MonthEntity monthEntity = new MonthEntity(month.getId(), month.getName(), month.getDayCount(), yearEntity);
                        dayWorkControllerDTOList.stream().filter(dayWork -> dayWork.getMonthControllerDTOId().equals(month.getId()))
                                .forEach(dayWork -> monthEntity.getDayWorkList()
                                        .add(new DayWorkEntity(dayWork.getId(), dayWork.getDateOfWorkDay(), monthEntity)));
                        dayWorkOutControllerDTOList.stream().filter(dayWorkOut -> dayWorkOut.getMonthControllerDTOId().equals(month.getId()))
                                .forEach(dayWorkOut -> monthEntity.getDayWorkOutList()
                                        .add(new DayWorkOutEntity(dayWorkOut.getId(), dayWorkOut.getDateOfWorkOutDay(), monthEntity)));
                        yearEntity.getMonthList().add(monthEntity);
                    });
            calendarEntity.getYearList().add(yearEntity);
        });
        dayControllerDTOList.forEach(day -> {
            DayEntity dayEntity = new DayEntity(day.getId(), day.getDayName(),
                    day.getWeekDayWorkOut(), calendarEntity);
            calendarEntity.getDayList().add(dayEntity);
            if (day.getId().equals(anchorDayId)) {
                calendarEntity.setAnchorWeekDay(dayEntity);
            }
        });
        return mapper.calendarEntityToCalendarControllerDTO(repository.save(calendarEntity));
    }
}
