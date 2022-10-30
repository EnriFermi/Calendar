package ru.study.webapp.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.study.webapp.model.database.DatabaseMapper;
import ru.study.webapp.model.database.DayDatabaseModel;
import ru.study.webapp.service.DayService;

import java.util.List;


@RestController
@ComponentScan("ru.study.webapp")
@RequestMapping("/calendar/Day")
public class DayController {
    private final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);
    private final DayService service;

    public DayController(DayService service) {
        this.service = service;
    }


    @GetMapping("/")
    public List<DayDatabaseModel> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DayDatabaseModel one(@PathVariable Long id) {
        return service.getOne(id);
    }

    @PostMapping("/")
    public DayDatabaseModel add(@RequestBody DayDatabaseModel DayDatabaseModel) {
        return service.addOne(DayDatabaseModel);
    }

    @PutMapping("/{id}")
    public DayDatabaseModel replaceCalendar(@RequestBody DayDatabaseModel DayDatabaseModel, @PathVariable Long id) {
        return service.updateOne(DayDatabaseModel, id);
    }

    @DeleteMapping("/{id}")
    DayDatabaseModel deleteCalendar(@PathVariable Long id) {
        return service.deleteOne(id);
    }
}