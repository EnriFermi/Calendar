package ru.study.webapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.study.webapp.controller.dto.DayControllerDTO;
import ru.study.webapp.service.DayService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/calendar/Day")
public class DayController {
    private final DayService service;

    public DayController(DayService service) {
        this.service = service;
    }


    @GetMapping("/")
    public List<DayControllerDTO> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DayControllerDTO one(@PathVariable Long id) {
        return service.getOne(id);
    }
    @GetMapping("/page")
    public List<DayControllerDTO> onePage(@RequestParam Long pageSize, @RequestParam Long pageNumber) {
        return service.getOnePage(pageSize, pageNumber);
    }
    @PostMapping("/")
    public DayControllerDTO add(@Valid @RequestBody DayControllerDTO DayControllerDTO) {
        return service.addOne(DayControllerDTO);
    }

    @PutMapping("/{id}")
    public DayControllerDTO replace(@Valid @RequestBody DayControllerDTO DayControllerDTO, @PathVariable Long id) {
        return service.updateOne(DayControllerDTO, id);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return service.deleteOne(id);
    }
}