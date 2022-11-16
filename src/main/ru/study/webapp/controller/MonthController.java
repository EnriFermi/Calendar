package ru.study.webapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.study.webapp.controller.dto.MonthControllerDTO;
import ru.study.webapp.service.MonthService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/calendar/Month")
public class MonthController {
    private final MonthService service;

    public MonthController(MonthService service) {
        this.service = service;
    }


    @GetMapping("/")
    public List<MonthControllerDTO> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MonthControllerDTO one(@PathVariable Long id) {
        return service.getOne(id);
    }
    @GetMapping("/page")
    public List<MonthControllerDTO> onePage(@RequestParam Long pageSize, @RequestParam Long pageNumber) {
        return service.getOnePage(pageSize, pageNumber);
    }
    @PostMapping("/")
    public MonthControllerDTO add(@Valid @RequestBody MonthControllerDTO MonthControllerDTO) {
        return service.addOne(MonthControllerDTO);
    }

    @PutMapping("/{id}")
    public MonthControllerDTO replace(@Valid @RequestBody MonthControllerDTO MonthControllerDTO, @PathVariable Long id) {
        return service.updateOne(MonthControllerDTO, id);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return service.deleteOne(id);
    }
}