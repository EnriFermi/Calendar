package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.exceptions.CalendarNotFoundException;
import ru.study.webapp.model.database.DatabaseMapper;
import ru.study.webapp.model.database.MonthDatabaseModel;
import ru.study.webapp.repository.CalendarRepository;
import ru.study.webapp.repository.MonthRepository;

import java.util.List;

@Service
@ComponentScan("ru.study.webapp")
public class MonthService {
    private final MonthRepository repository;
    private final CalendarRepository calendarRepository;
    private final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);

    public MonthService(MonthRepository repository, CalendarRepository calendarRepository) {
        this.repository = repository;
        this.calendarRepository = calendarRepository;
    }
    @Transactional
    public List<MonthDatabaseModel> getAll() {
        List<MonthDatabaseModel> Months = (List<MonthDatabaseModel>) repository.findAll();

        return ((List<MonthDatabaseModel>) repository.findAll()).stream().toList();
    }

    @Transactional
    public MonthDatabaseModel getOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new CalendarNotFoundException(id));
    }
    @Transactional
    public MonthDatabaseModel addOne(MonthDatabaseModel MonthDatabaseModel) {
        MonthDatabaseModel MonthDatabaseModel1 = new MonthDatabaseModel();
        mapper.updateMonthDatabaseModel(MonthDatabaseModel, MonthDatabaseModel1);
        return repository.save(MonthDatabaseModel1);
    }
    @Transactional
    public MonthDatabaseModel updateOne(MonthDatabaseModel MonthDatabaseModel, Long id) {
        return repository.findById(id)
                .map(Month -> {
                    Month.setName(MonthDatabaseModel.getName());
                    Month.setDayCount(MonthDatabaseModel.getDayCount());
                    Month.setYearDatabaseModel(MonthDatabaseModel.getYearDatabaseModel());
                    return repository.save(Month);
                })
                .orElseGet(() -> {
                    MonthDatabaseModel.setId(id);
                    return repository.save(MonthDatabaseModel);
                });
    }
    @Transactional
    public MonthDatabaseModel deleteOne(Long id) {
        if (!repository.existsById(id)) {
            throw new CalendarNotFoundException(id);
        } else {
            repository.deleteById(id);
            return new MonthDatabaseModel();
        }
    }
}

