package ru.study.webapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.study.webapp.controller.dto.YearControllerDTO;
import ru.study.webapp.service.YearService;

import javax.validation.Valid;
import java.util.List;

@RestController
//TODO почитать, не должно быть в каждом классе по componentscan DONE
@RequestMapping("/calendar/year")
public class YearController {
    private final YearService service;

    public YearController(YearService service) {
        this.service = service;
    }


    @GetMapping("/")
    public List<YearControllerDTO> all() {
        return service.getAll();
    }
    @GetMapping("/page")
    public List<YearControllerDTO> onePage(@RequestParam Long pageSize, @RequestParam Long pageNumber) {
        return service.getOnePage(pageSize, pageNumber);
    }
    @GetMapping("/{id}")
    public YearControllerDTO one(@PathVariable Long id) {
        return service.getOne(id);
    }

    @PostMapping("/")
    public YearControllerDTO add(@Valid @RequestBody YearControllerDTO yearControllerDTO) {
        return service.addOne(yearControllerDTO);
    }

    @PutMapping("/{id}")
    public YearControllerDTO replace(@Valid @RequestBody YearControllerDTO yearControllerDTO, @PathVariable Long id) {
        return service.updateOne(yearControllerDTO, id);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return service.deleteOne(id);
    }
}