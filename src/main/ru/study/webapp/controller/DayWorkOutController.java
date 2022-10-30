package ru.study.webapp.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.study.webapp.model.database.DatabaseMapper;
import ru.study.webapp.model.database.DayWorkOutDatabaseModel;
import ru.study.webapp.service.DayWorkOutService;

import java.util.List;


@RestController
@ComponentScan("ru.study.webapp")
@RequestMapping("/calendar/DayWorkOut")
public class DayWorkOutController {
    private final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);
    private final DayWorkOutService service;

    public DayWorkOutController(DayWorkOutService service) {
        this.service = service;
    }


    @GetMapping("/")
    public List<DayWorkOutDatabaseModel> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DayWorkOutDatabaseModel one(@PathVariable Long id) {
        return service.getOne(id);
    }

    @PostMapping("/")
    public DayWorkOutDatabaseModel add(@RequestBody DayWorkOutDatabaseModel DayWorkOutDatabaseModel) {
        return service.addOne(DayWorkOutDatabaseModel);
    }

    @PutMapping("/{id}")
    public DayWorkOutDatabaseModel replaceCalendar(@RequestBody DayWorkOutDatabaseModel DayWorkOutDatabaseModel, @PathVariable Long id) {
        return service.updateOne(DayWorkOutDatabaseModel, id);
    }

    @DeleteMapping("/{id}")
    DayWorkOutDatabaseModel deleteCalendar(@PathVariable Long id) {
        return service.deleteOne(id);
    }
}