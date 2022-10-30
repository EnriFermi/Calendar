package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.exceptions.CalendarNotFoundException;
import ru.study.webapp.model.database.DatabaseMapper;
import ru.study.webapp.model.database.DayWorkOutDatabaseModel;
import ru.study.webapp.repository.CalendarRepository;
import ru.study.webapp.repository.DayWorkOutRepository;

import java.util.List;

@Service
@ComponentScan("ru.study.webapp")
public class DayWorkOutService {
    private final DayWorkOutRepository repository;
    private final CalendarRepository calendarRepository;
    private final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);

    public DayWorkOutService(DayWorkOutRepository repository, CalendarRepository calendarRepository) {
        this.repository = repository;
        this.calendarRepository = calendarRepository;
    }
    @Transactional
    public List<DayWorkOutDatabaseModel> getAll() {
        List<DayWorkOutDatabaseModel> DayWorkOuts = (List<DayWorkOutDatabaseModel>) repository.findAll();

        return ((List<DayWorkOutDatabaseModel>) repository.findAll()).stream().toList();
    }

    @Transactional
    public DayWorkOutDatabaseModel getOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new CalendarNotFoundException(id));
    }
    @Transactional
    public DayWorkOutDatabaseModel addOne(DayWorkOutDatabaseModel DayWorkOutDatabaseModel) {
        DayWorkOutDatabaseModel DayWorkOutDatabaseModel1 = new DayWorkOutDatabaseModel();
        mapper.updateDayWorkOutDatabaseModel(DayWorkOutDatabaseModel, DayWorkOutDatabaseModel1);
        return repository.save(DayWorkOutDatabaseModel1);
    }
    @Transactional
    public DayWorkOutDatabaseModel updateOne(DayWorkOutDatabaseModel DayWorkOutDatabaseModel, Long id) {
        return repository.findById(id)
                .map(DayWorkOut -> {
                    DayWorkOut.setDateOfWorkOutDay(DayWorkOutDatabaseModel.getDateOfWorkOutDay());
                    DayWorkOut.setMonthDatabaseModel(DayWorkOutDatabaseModel.getMonthDatabaseModel());
                    return repository.save(DayWorkOut);
                })
                .orElseGet(() -> {
                    DayWorkOutDatabaseModel.setId(id);
                    return repository.save(DayWorkOutDatabaseModel);
                });
    }
    @Transactional
    public DayWorkOutDatabaseModel deleteOne(Long id) {
        if (!repository.existsById(id)) {
            throw new CalendarNotFoundException(id);
        } else {
            repository.deleteById(id);
            return new DayWorkOutDatabaseModel();
        }
    }
}
