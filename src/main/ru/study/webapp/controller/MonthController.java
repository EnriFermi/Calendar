package ru.study.webapp.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.study.webapp.model.database.DatabaseMapper;
import ru.study.webapp.model.database.MonthDatabaseModel;
import ru.study.webapp.service.MonthService;

import java.util.List;


@RestController
@ComponentScan("ru.study.webapp")
@RequestMapping("/calendar/Month")
public class MonthController {
    private final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);
    private final MonthService service;

    public MonthController(MonthService service) {
        this.service = service;
    }


    @GetMapping("/")
    public List<MonthDatabaseModel> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MonthDatabaseModel one(@PathVariable Long id) {
        return service.getOne(id);
    }

    @PostMapping("/")
    public MonthDatabaseModel add(@RequestBody MonthDatabaseModel MonthDatabaseModel) {
        return service.addOne(MonthDatabaseModel);
    }

    @PutMapping("/{id}")
    public MonthDatabaseModel replaceCalendar(@RequestBody MonthDatabaseModel MonthDatabaseModel, @PathVariable Long id) {
        return service.updateOne(MonthDatabaseModel, id);
    }

    @DeleteMapping("/{id}")
    MonthDatabaseModel deleteCalendar(@PathVariable Long id) {
        return service.deleteOne(id);
    }
}