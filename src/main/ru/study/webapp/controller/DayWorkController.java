package ru.study.webapp.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.study.webapp.model.database.DatabaseMapper;
import ru.study.webapp.model.database.DayWorkDatabaseModel;
import ru.study.webapp.service.DayWorkService;

import java.util.List;


@RestController
@ComponentScan("ru.study.webapp")
@RequestMapping("/calendar/DayWork")
public class DayWorkController {
    private final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);
    private final DayWorkService service;

    public DayWorkController(DayWorkService service) {
        this.service = service;
    }


    @GetMapping("/")
    public List<DayWorkDatabaseModel> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DayWorkDatabaseModel one(@PathVariable Long id) {
        return service.getOne(id);
    }

    @PostMapping("/")
    public DayWorkDatabaseModel add(@RequestBody DayWorkDatabaseModel DayWorkDatabaseModel) {
        return service.addOne(DayWorkDatabaseModel);
    }

    @PutMapping("/{id}")
    public DayWorkDatabaseModel replaceCalendar(@RequestBody DayWorkDatabaseModel DayWorkDatabaseModel, @PathVariable Long id) {
        return service.updateOne(DayWorkDatabaseModel, id);
    }

    @DeleteMapping("/{id}")
    DayWorkDatabaseModel deleteCalendar(@PathVariable Long id) {
        return service.deleteOne(id);
    }
}