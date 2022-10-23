package ru.study.webapp.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import ru.study.webapp.controller.CalendarController;
import ru.study.webapp.model.database.CalendarDatabaseModel;
import ru.study.webapp.model.database.DatabaseMapper;
import ru.study.webapp.exceptions.CalendarNotFoundException;
import ru.study.webapp.model.configuration.domain.YearTemplate;
import ru.study.webapp.repository.YearRepository;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@ComponentScan("ru.study.webapp")
@RequestMapping("/year")
public class YearController {
    private final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);
    private final YearRepository repository;


    YearController(YearRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<YearTemplate all() {

    }

    @GetMapping("/{id}")
    public EntityModel<CalendarDatabaseModel> one(@PathVariable Long id) {
        return assembler.toModel(repository.findById(id)
                .orElseThrow(() -> new CalendarNotFoundException(id)));
    }

    @PostMapping("/")
    public EntityModel<CalendarDatabaseModel> newEmployee(@RequestBody CalendarDatabaseModel calendarDatabaseModel) {
        for (int i = 0; i < calendarDatabaseModel.getDayList().size(); i++) {
            if (calendarDatabaseModel.getDayList().get(i).equals(calendarDatabaseModel.getAnchorWeekDay())) {
                calendarDatabaseModel.setAnchorWeekDay(calendarDatabaseModel.getDayList().get(i));
                break;
            }
        }
        return assembler.toModel(repository.save(calendarDatabaseModel));
    }

    @PutMapping("/{id}")
    //TODO архитектура: контроллер -> сервис(если надо открывает тразакцию) -> репозиториями
    //контроллер (ДТО - json + schema) -> сервис( доменная модель, чисто класс с getter+setter) -> репозиториями (entity -> БД)
    public EntityModel<CalendarDatabaseModel> replaceCalendar(@RequestBody CalendarDatabaseModel calendarDatabaseModel, @PathVariable Long id) {

        //TODO можно разбить на кучу контроллеров, например меняющий с какого дня недели стартует 0 год
        for (int i = 0; i < calendarDatabaseModel.getDayList().size(); i++) {
            if (calendarDatabaseModel.getDayList().get(i).equals(calendarDatabaseModel.getAnchorWeekDay())) {
                calendarDatabaseModel.setAnchorWeekDay(calendarDatabaseModel.getDayList().get(i));
                break;
            }
        }

        //TODO если решил делать все на все, тогда надо calendarDAO = repository.findById(id), и в него прокидывать простые объекты при различии
        // а если зависимый объект отличается, то надо конкретный заменять


        //контроллер (ДТО - json + schema) -> сервис( доменная модель, чисто класс с getter+setter) -> репозиториями (entity -> БД)/
        // при парсинге в доменную проверяешь допустимость изменений. А ее заливаешь в существующую энтити.
        return repository.findById(id)
                .map(calendar -> {
                    mapper.updateCalendarDAO(calendarDatabaseModel, calendar);
                    return assembler.toModel(repository.save(calendar));
                })
                .orElseGet(() -> {
                    calendarDatabaseModel.setId(id);
                    return assembler.toModel(repository.save(calendarDatabaseModel));
                });
    }

    @DeleteMapping("/{id}")
    EntityModel<CalendarDatabaseModel> deleteCalendar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new CalendarNotFoundException(id);
        } else {
            repository.deleteById(id);
            return EntityModel.of(new CalendarDatabaseModel(), WebMvcLinkBuilder.linkTo(methodOn(CalendarController.class).all()).withRel("calendar"));
        }
    }
}