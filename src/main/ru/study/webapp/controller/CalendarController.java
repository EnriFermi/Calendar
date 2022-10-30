package ru.study.webapp.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.study.webapp.model.database.CalendarDatabaseModel;
import ru.study.webapp.service.CalendarService;

import java.util.List;


@RestController
@ComponentScan("ru.study.webapp")
@RequestMapping("/calendar")
public class CalendarController {
  public final CalendarService service;

  public CalendarController(CalendarService service) {
    this.service = service;
  }

  @GetMapping("/")
    public List<CalendarDatabaseModel> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CalendarDatabaseModel one(@PathVariable Long id) {
        return service.getOne(id);
    }

    @PostMapping("/")
    public CalendarDatabaseModel add(@RequestBody CalendarDatabaseModel calendarDatabaseModel) {
        return service.addOne(calendarDatabaseModel);
    }

    @PutMapping("/{id}")
    //TODO архитектура: контроллер -> сервис(если надо открывает тразакцию) -> репозиториями
    // контроллер (ДТО - json + schema) -> сервис( доменная модель, чисто класс с getter+setter) -> репозиториями (entity -> БД)
    public CalendarDatabaseModel update(@RequestBody CalendarDatabaseModel calendarDatabaseModel, @PathVariable Long id) {

        //TODO можно разбить на кучу контроллеров, например меняющий с какого дня недели стартует 0 год
        //TODO если решил делать все на все, тогда надо calendarDAO = repository.findById(id), и в него прокидывать простые объекты при различии
        // а если зависимый объект отличается, то надо конкретный заменять

        // TODO контроллер (ДТО - json + schema) -> сервис( доменная модель, чисто класс с getter+setter) -> репозиториями (entity -> БД)
        //  при парсинге в доменную проверяешь допустимость изменений. А ее заливаешь в существующую энтити. ?(как лучше)
        return service.updateOne(calendarDatabaseModel, id);
    }

    @DeleteMapping("/{id}")
    public CalendarDatabaseModel delete(@PathVariable Long id) {
        return service.deleteOne(id);
    }

    @PutMapping("/anchorday/{id}")
    public CalendarDatabaseModel chgDay(@RequestParam Long anchorId, @PathVariable Long id) {
        return service.setAnchorDay(id, anchorId);
    }
}