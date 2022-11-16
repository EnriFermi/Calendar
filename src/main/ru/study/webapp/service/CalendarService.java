package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.controller.dto.CalendarControllerDTO;
import ru.study.webapp.controller.dto.DayControllerDTO;
import ru.study.webapp.exceptions.NotFoundException;
import ru.study.webapp.model.database.CalendarDatabaseModel;
import ru.study.webapp.model.database.DayDatabaseModel;
import ru.study.webapp.model.mappers.DatabaseDTOMapper;
import ru.study.webapp.repository.CalendarRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service

public class CalendarService {
    private final CalendarRepository repository;
    private final DatabaseDTOMapper mapper = Mappers.getMapper(DatabaseDTOMapper.class);

    public CalendarService(CalendarRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<CalendarControllerDTO> getAll(){
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::calendarDatabaseModelToCalendarControllerDTO).toList();
    }
    @Transactional
    public List<CalendarControllerDTO> getOnePage(Long pageSize, Long pageNumber){
        Page<CalendarDatabaseModel> res = repository.findAll(
                PageRequest.of(pageNumber.intValue()-1, pageSize.intValue()));
        //TODO не ругаться. а вернуть пустой список
        if(res.getTotalPages() < pageNumber.intValue()){
            throw new NotFoundException(CalendarControllerDTO.class, "Номер страницы превышает их общее количество");
        }
        return StreamSupport.stream(res.spliterator(), false)
                .map(mapper::calendarDatabaseModelToCalendarControllerDTO).toList();
    }
    @Transactional
    public CalendarControllerDTO getOne(Long id){
        return mapper.calendarDatabaseModelToCalendarControllerDTO(repository.findById(id)
            .orElseThrow(() -> new NotFoundException(CalendarControllerDTO.class, id)));
    }

    @Transactional
    public CalendarControllerDTO addOne(CalendarControllerDTO calendarControllerDTO){
    return mapper.calendarDatabaseModelToCalendarControllerDTO(
            repository.save(mapper.calendarControllerDTOToCalendarDatabaseModel(calendarControllerDTO)));
    }
    @Transactional
    public CalendarControllerDTO setAnchorDay(Long calendarId, Long dayId){
         return mapper.calendarDatabaseModelToCalendarControllerDTO(repository.findById(calendarId).map(calendar -> {
                    for(DayDatabaseModel day: calendar.getDayList()){
                        if(day.getId().equals(dayId)){
                            calendar.setAnchorWeekDay(day);
                            return repository.save(calendar);
                        }
                    }
                    throw new NotFoundException(CalendarControllerDTO.class, calendarId);
                }).orElseThrow());
    }
    @Transactional
    public CalendarControllerDTO updateOne(CalendarControllerDTO calendarControllerDTO, Long id) {
        return mapper.calendarDatabaseModelToCalendarControllerDTO(repository.findById(id)
            .map(calendar -> {
                calendar.setBeginningYear(calendarControllerDTO.getBeginningYear());
                calendar.setEndYear(calendarControllerDTO.getEndYear());
                return repository.save(calendar);
            })
            .orElseGet(() -> {
                //TODO если не нашли по ИД, должна быть ошибка
                calendarControllerDTO.setId(id);
                return repository.save(mapper.calendarControllerDTOToCalendarDatabaseModel(calendarControllerDTO));
            }));
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
        return mapper.dayDatabaseModelToDayControllerDTO(repository.findById(id).orElseThrow().getAnchorWeekDay());
    }
}
