package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.exceptions.CalendarNotFoundException;
import ru.study.webapp.model.database.CalendarDatabaseModel;
import ru.study.webapp.model.database.DatabaseMapper;
import ru.study.webapp.model.database.DayDatabaseModel;
import ru.study.webapp.repository.CalendarRepository;

import java.util.List;

@Service
@ComponentScan("ru.study.webapp")
public class CalendarService {
    private final CalendarRepository repository;
    private final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);

    public CalendarService(CalendarRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<CalendarDatabaseModel> getAll() {
        List<CalendarDatabaseModel> calendars = (List<CalendarDatabaseModel>) repository.findAll();

        return ((List<CalendarDatabaseModel>) repository.findAll()).stream().toList();
    }

    @Transactional
    public CalendarDatabaseModel getOne(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new CalendarNotFoundException(id));
    }

    @Transactional
    public CalendarDatabaseModel addOne(CalendarDatabaseModel calendarDatabaseModel) {
        return repository.save(calendarDatabaseModel);
    }
    @Transactional
    public CalendarDatabaseModel setAnchorDay(Long calendarId, Long dayId){
         return repository.findById(calendarId).map(calendar -> {
                    for(DayDatabaseModel day: calendar.getDayList()){
                        if(day.getId().equals(dayId)){
                            calendar.setAnchorWeekDay(day);
                            return repository.save(calendar);
                        }
                    }
                    throw new CalendarNotFoundException(calendarId);//FIXME replace exception
                }).orElseThrow();
    }
    @Transactional
    public CalendarDatabaseModel updateOne(CalendarDatabaseModel calendarDatabaseModel, Long id) {
        return repository.findById(id)
            .map(calendar -> {
                calendar.setBeginningYear(calendarDatabaseModel.getBeginningYear());
                calendar.setEndYear(calendarDatabaseModel.getEndYear());
                return repository.save(calendar);
            })
            .orElseGet(() -> {
                calendarDatabaseModel.setId(id);
                return repository.save(calendarDatabaseModel);
            });
    }
    @Transactional
    public CalendarDatabaseModel deleteOne(Long id) {
        if (!repository.existsById(id)) {
            throw new CalendarNotFoundException(id);
        } else {
            repository.deleteById(id);
            return new CalendarDatabaseModel();
        }
    }
}
