package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.controller.dto.DayControllerDTO;
import ru.study.webapp.exceptions.NotFoundException;
import ru.study.webapp.model.database.CalendarDatabaseModel;
import ru.study.webapp.model.database.DayDatabaseModel;
import ru.study.webapp.model.mappers.DatabaseDTOMapper;
import ru.study.webapp.repository.DayRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class DayService {
    private final DayRepository repository;
    private final DatabaseDTOMapper mapper = Mappers.getMapper(DatabaseDTOMapper.class);

    public DayService(DayRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public List<DayControllerDTO> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::dayDatabaseModelToDayControllerDTO).toList();
    }

    @Transactional
    public DayControllerDTO getOne(Long id) {
        return mapper.dayDatabaseModelToDayControllerDTO(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(DayControllerDTO.class, id)));
    }
    @Transactional
    public List<DayControllerDTO> getOnePage(Long pageSize, Long pageNumber){
        Page<DayDatabaseModel> res = repository.findAll(
                PageRequest.of(pageNumber.intValue()-1, pageSize.intValue()));
        if(res.getTotalPages() < pageNumber.intValue()){
            throw new NotFoundException(DayControllerDTO.class, "Номер страницы превышает их общее количество");
        }
        return StreamSupport.stream(res.spliterator(), false)
                .map(mapper::dayDatabaseModelToDayControllerDTO).toList();
    }
    @Transactional
    public DayControllerDTO addOne(DayControllerDTO DayControllerDTO) {
        return mapper.dayDatabaseModelToDayControllerDTO(
                repository.save(mapper.dayControllerDTOToDayDatabaseModel(DayControllerDTO)));
    }
    @Transactional
    public DayControllerDTO updateOne(DayControllerDTO DayControllerDTO, Long id) {
        return mapper.dayDatabaseModelToDayControllerDTO(repository.findById(id)
                .map(dayDatabaseModel -> {
                    dayDatabaseModel.setDayName(DayControllerDTO.getDayName());
                    dayDatabaseModel.setWeekDayWorkOut(DayControllerDTO.getWeekDayWorkOut());
                    dayDatabaseModel.setCalendarDatabaseModel(new CalendarDatabaseModel(DayControllerDTO
                            .getCalendarControllerDTOId()));
                    return repository.save(dayDatabaseModel);
                })
                .orElseGet(() -> repository.save(mapper.dayControllerDTOToDayDatabaseModel(DayControllerDTO))));
    }
    @Transactional
    public Long deleteOne(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(DayControllerDTO.class,id);
        } else {
            repository.deleteById(id);
            return id; //How?
        }
    }
}
