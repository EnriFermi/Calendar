package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.exceptions.CalendarNotFoundException;
import ru.study.webapp.model.database.DatabaseMapper;
import ru.study.webapp.model.database.DayWorkDatabaseModel;
import ru.study.webapp.repository.CalendarRepository;
import ru.study.webapp.repository.DayWorkRepository;

import java.util.List;

@Service
@ComponentScan("ru.study.webapp")
public class DayWorkService {
    private final DayWorkRepository repository;
    private final CalendarRepository calendarRepository;
    private final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);

    public DayWorkService(DayWorkRepository repository, CalendarRepository calendarRepository) {
        this.repository = repository;
        this.calendarRepository = calendarRepository;
    }
    @Transactional
    public List<DayWorkDatabaseModel> getAll() {
        List<DayWorkDatabaseModel> DayWorks = (List<DayWorkDatabaseModel>) repository.findAll();

        return ((List<DayWorkDatabaseModel>) repository.findAll()).stream().toList();
    }

    @Transactional
    public DayWorkDatabaseModel getOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new CalendarNotFoundException(id));
    }
    @Transactional
    public DayWorkDatabaseModel addOne(DayWorkDatabaseModel DayWorkDatabaseModel) {
        DayWorkDatabaseModel DayWorkDatabaseModel1 = new DayWorkDatabaseModel();
        mapper.updateDayWorkDatabaseModel(DayWorkDatabaseModel, DayWorkDatabaseModel1);
        return repository.save(DayWorkDatabaseModel1);
    }
    @Transactional
    public DayWorkDatabaseModel updateOne(DayWorkDatabaseModel DayWorkDatabaseModel, Long id) {
        return repository.findById(id)
                .map(DayWork -> {
                    DayWork.setDateOfWorkDay(DayWorkDatabaseModel.getDateOfWorkDay());
                    DayWork.setMonthDatabaseModel(DayWorkDatabaseModel.getMonthDatabaseModel());
                    return repository.save(DayWork);
                })
                .orElseGet(() -> {
                    DayWorkDatabaseModel.setId(id);
                    return repository.save(DayWorkDatabaseModel);
                });
    }
    @Transactional
    public DayWorkDatabaseModel deleteOne(Long id) {
        if (!repository.existsById(id)) {
            throw new CalendarNotFoundException(id);
        } else {
            repository.deleteById(id);
            return new DayWorkDatabaseModel();
        }
    }
}
