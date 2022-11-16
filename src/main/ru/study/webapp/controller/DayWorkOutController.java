package ru.study.webapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.study.webapp.controller.dto.DayWorkOutControllerDTO;
import ru.study.webapp.service.DayWorkOutService;

import javax.validation.Valid;
import java.util.List;


@RestController

@RequestMapping("/calendar/DayWorkOut")
public class DayWorkOutController {
    private final DayWorkOutService service;

    public DayWorkOutController(DayWorkOutService service) {
        this.service = service;
    }


    @GetMapping("/")
    public List<DayWorkOutControllerDTO> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DayWorkOutControllerDTO one(@PathVariable Long id) {
        return service.getOne(id);
    }
    @GetMapping("/page")
    public List<DayWorkOutControllerDTO> onePage(@RequestParam Long pageSize, @RequestParam Long pageNumber) {
        return service.getOnePage(pageSize, pageNumber);
    }
    @PostMapping("/")
    public DayWorkOutControllerDTO add(@Valid @RequestBody DayWorkOutControllerDTO DayWorkOutControllerDTO) {
        return service.addOne(DayWorkOutControllerDTO);
    }

    @PutMapping("/{id}")
    public DayWorkOutControllerDTO replace(@Valid @RequestBody DayWorkOutControllerDTO DayWorkOutControllerDTO, @PathVariable Long id) {
        return service.updateOne(DayWorkOutControllerDTO, id);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return service.deleteOne(id);
    }
}