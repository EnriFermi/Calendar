package ru.study.webapp.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.study.webapp.model.database.DatabaseMapper;
import ru.study.webapp.model.database.YearDatabaseModel;
import ru.study.webapp.service.YearService;

import java.util.List;

@RestController
//TODO почитать, не должно быть в каждом классе по componentscan
@ComponentScan("ru.study.webapp")
@RequestMapping("/calendar/year")
public class YearController {
    private final YearService service;

    public YearController(YearService service) {
        this.service = service;
    }


    @GetMapping("/")
    public List<YearDatabaseModel> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public YearDatabaseModel one(@PathVariable Long id) {
        return service.getOne(id);
    }

    @PostMapping("/")
    public YearDatabaseModel add(@RequestBody YearDatabaseModel yearDatabaseModel) {
        return service.addOne(yearDatabaseModel);
    }

    @PutMapping("/{id}")
    public YearDatabaseModel replaceCalendar(@RequestBody YearDatabaseModel yearDatabaseModel, @PathVariable Long id) {
        return service.updateOne(yearDatabaseModel, id);
    }

    @DeleteMapping("/{id}")
    YearDatabaseModel deleteCalendar(@PathVariable Long id) {
        return service.deleteOne(id);
    }
}