package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.exceptions.CalendarNotFoundException;
import ru.study.webapp.model.database.DatabaseMapper;
import ru.study.webapp.model.database.DayDatabaseModel;
import ru.study.webapp.repository.CalendarRepository;
import ru.study.webapp.repository.DayRepository;

import java.util.List;

@Service
@ComponentScan("ru.study.webapp")
public class DayService {
    private final DayRepository repository;
    private final CalendarRepository calendarRepository;
    private final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);

    public DayService(DayRepository repository, CalendarRepository calendarRepository) {
        this.repository = repository;
        this.calendarRepository = calendarRepository;
    }
    @Transactional
    public List<DayDatabaseModel> getAll() {
        List<DayDatabaseModel> Days = (List<DayDatabaseModel>) repository.findAll();

        return ((List<DayDatabaseModel>) repository.findAll()).stream().toList();
    }

    @Transactional
    public DayDatabaseModel getOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new CalendarNotFoundException(id));
    }
    @Transactional
    public DayDatabaseModel addOne(DayDatabaseModel DayDatabaseModel) {
        DayDatabaseModel DayDatabaseModel1 = new DayDatabaseModel();
        mapper.updateDayDatabaseModel(DayDatabaseModel, DayDatabaseModel1);
        return repository.save(DayDatabaseModel1);
    }
    @Transactional
    public DayDatabaseModel updateOne(DayDatabaseModel DayDatabaseModel, Long id) {
        return repository.findById(id)
                .map(Day -> {
                    Day.setCalendarDatabaseModel(DayDatabaseModel.getCalendarDatabaseModel());
                    Day.setDayName(DayDatabaseModel.getDayName());
                    Day.setWeekDayWorkOut(DayDatabaseModel.getWeekDayWorkOut());
                    return repository.save(Day);
                })
                .orElseGet(() -> {
                    DayDatabaseModel.setId(id);
                    return repository.save(DayDatabaseModel);
                });
    }
    @Transactional
    public DayDatabaseModel deleteOne(Long id) {
        if (!repository.existsById(id)) {
            throw new CalendarNotFoundException(id);
        } else {
            repository.deleteById(id);
            return new DayDatabaseModel();
        }
    }
}
