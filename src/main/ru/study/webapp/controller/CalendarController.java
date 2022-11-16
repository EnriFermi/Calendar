package ru.study.webapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.study.webapp.controller.dto.CalendarControllerDTO;
import ru.study.webapp.controller.dto.DayControllerDTO;
import ru.study.webapp.service.CalendarService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/calendar")
public class CalendarController {
    public final CalendarService service;

    public CalendarController(CalendarService service) {
        this.service = service;
    }

    @Operation(summary = "Получить все календари в БД")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Календари успешно найдены",
                    content = { @Content(mediaType = "application/json", array =
                    @ArraySchema(schema = @Schema(implementation = CalendarControllerDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "-",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "-",
                    content = @Content)})
    @GetMapping("/")
    public List<CalendarControllerDTO> all() {
        return service.getAll();
    }

    @Operation(summary = "Получить страницу из списка календаре по ее номеру из БД")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница найдена",
                    content = { @Content(mediaType = "application/json", array =
                    @ArraySchema(schema = @Schema(implementation = CalendarControllerDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "Старница не найдена",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "-",
            content = @Content)
    })
    @GetMapping("/page")
    //TODO добавить поиск ло опциональным полям:  beginningYear, endYear, наличие в календаре месяца с названием
    // TODO возможность сортировки по полям:  beginningYear, endYear
    public List<CalendarControllerDTO> onePage(@RequestParam Long pageSize, @RequestParam Long pageNumber) {
        return service.getOnePage(pageSize, pageNumber);
    }

    @Operation(summary = "Получить календарь по его id из БД")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Календарь найден",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = CalendarControllerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Календарь не найден",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "-",
            content = @Content) })
    @GetMapping("/{id}")
    public CalendarControllerDTO one(@PathVariable Long id) {
        return service.getOne(id);
    }

    @Operation(summary = "Получить из БД привязочный день по id календаря, к которому привязан день")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "День найден",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = CalendarControllerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "День не найден",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "-",
                    content = @Content)})
    @GetMapping("/anchorday/{id}")
    public DayControllerDTO getDay(@PathVariable Long id) {
        return service.getAnchorDay(id);
    }
    @Operation(summary = "Добавить новый календарь в БД")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно добавлен",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = CalendarControllerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "-",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Введенные данные не корректны",
                    content = @Content)})
    @PostMapping("/")
    public CalendarControllerDTO add(@Valid @RequestBody CalendarControllerDTO calendarControllerDTO) {
        return service.addOne(calendarControllerDTO);
    }

    @Operation(summary = "Обновить календарь в БД по его id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно обновлен",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = CalendarControllerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "-",
                    content = @Content),
    //TODO почитать https://www.baeldung.com/exception-handling-for-rest-with-spring
            @ApiResponse(responseCode = "400", description = "Не корректные данные",
                    content = @Content)})
    @PutMapping("/{id}")
    public CalendarControllerDTO update(@Valid @RequestBody CalendarControllerDTO calendarControllerDTO, @PathVariable Long id) {
        return service.updateOne(calendarControllerDTO, id);
    }

    @Operation(summary = "Удалить календарь из БД по его id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Календарь найден",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = CalendarControllerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Календарь не найден",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "-",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return service.deleteOne(id);
    }

    @Operation(summary = "Изменить из БД привязочный день по id календаря, к которому привязан день")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "День успешно изменен",
                    content = { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = CalendarControllerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "День или календарь с такими id не найдены",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "-",
                    content = @Content)})
    @PutMapping("/anchorday/{id}")
    public CalendarControllerDTO chgDay(@RequestParam Long anchorId, @PathVariable Long id) {
        return service.setAnchorDay(id, anchorId);
    }
}