package ru.study.webapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.study.webapp.controller.dto.DayWorkControllerDTO;
import ru.study.webapp.service.DayWorkService;

import javax.validation.Valid;
import java.util.List;


@RestController

@RequestMapping("/calendar/DayWork")
public class DayWorkController {
    private final DayWorkService service;

    public DayWorkController(DayWorkService service) {
        this.service = service;
    }


    @GetMapping("/")
    public List<DayWorkControllerDTO> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DayWorkControllerDTO one(@PathVariable Long id) {
        return service.getOne(id);
    }
    @GetMapping("/page")
    public List<DayWorkControllerDTO> onePage(@RequestParam Long pageSize, @RequestParam Long pageNumber) {
        return service.getOnePage(pageSize, pageNumber);
    }
    @PostMapping("/")
    public DayWorkControllerDTO add(@Valid @RequestBody DayWorkControllerDTO DayWorkControllerDTO) {
        return service.addOne(DayWorkControllerDTO);
    }

    @PutMapping("/{id}")
    public DayWorkControllerDTO replace(@Valid @RequestBody DayWorkControllerDTO DayWorkControllerDTO, @PathVariable Long id) {
        return service.updateOne(DayWorkControllerDTO, id);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return service.deleteOne(id);
    }
}