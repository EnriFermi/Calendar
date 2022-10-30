package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.exceptions.CalendarNotFoundException;
import ru.study.webapp.model.database.DatabaseMapper;
import ru.study.webapp.model.database.YearDatabaseModel;
import ru.study.webapp.repository.CalendarRepository;
import ru.study.webapp.repository.YearRepository;

import java.util.List;

@Service
@ComponentScan("ru.study.webapp")
public class YearService {
    private final YearRepository repository;
    private final CalendarRepository calendarRepository;
    private final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);

    public YearService(YearRepository repository, CalendarRepository calendarRepository) {
        this.repository = repository;
        this.calendarRepository = calendarRepository;
    }
    @Transactional
    public List<YearDatabaseModel> getAll() {
        List<YearDatabaseModel> Years = (List<YearDatabaseModel>) repository.findAll();

        return ((List<YearDatabaseModel>) repository.findAll()).stream().toList();
    }

    @Transactional
    public YearDatabaseModel getOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new CalendarNotFoundException(id));
    }
    @Transactional
    public YearDatabaseModel addOne(YearDatabaseModel yearDatabaseModel) {
        YearDatabaseModel yearDatabaseModel1 = new YearDatabaseModel();
        mapper.updateYearDatabaseModel(yearDatabaseModel, yearDatabaseModel1);
        return repository.save(yearDatabaseModel1);
    }
    @Transactional
    public YearDatabaseModel updateOne(YearDatabaseModel YearDatabaseModel, Long id) {
        return repository.findById(id)
                .map(Year -> {
                    Year.setCalendarDatabaseModel(YearDatabaseModel.getCalendarDatabaseModel());
                    return repository.save(Year);
                })
                .orElseGet(() -> {
                    YearDatabaseModel.setId(id);
                    return repository.save(YearDatabaseModel);
                });
    }
    @Transactional
    public YearDatabaseModel deleteOne(Long id) {
        if (!repository.existsById(id)) {
            throw new CalendarNotFoundException(id);
        } else {
            repository.deleteById(id);
            return new YearDatabaseModel();
        }
    }
}
