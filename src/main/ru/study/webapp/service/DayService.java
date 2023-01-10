package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.controller.dto.DayControllerDTO;
import ru.study.webapp.exceptions.NotFoundException;
import ru.study.webapp.repository.entity.CalendarEntity;
import ru.study.webapp.repository.entity.DayEntity;
import ru.study.webapp.model.mappers.DatabaseDTOMapper;
import ru.study.webapp.repository.DayRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
//TODO  сделать интерфейсы ВСЕХ сервисов и их использовать в других бинах
public class DayService {
    private final DayRepository repository;
    private final DatabaseDTOMapper mapper = Mappers.getMapper(DatabaseDTOMapper.class);

    public DayService(DayRepository repository) {
        this.repository = repository;
    }
    @Transactional(readOnly = true)
    public List<DayControllerDTO> getAll() {
        //TODO зачем?
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::dayEntityToDayControllerDTO).toList();
    }

    @Transactional
    public DayControllerDTO getOne(Long id) {
        return mapper.dayEntityToDayControllerDTO(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(DayControllerDTO.class, id)));
    }
    @Transactional
    public List<DayControllerDTO> getOnePage(Long pageSize, Long pageNumber){
        Page<DayEntity> res = repository.findAll(
                PageRequest.of(pageNumber.intValue()-1, pageSize.intValue()));
        if(res.getTotalPages() < pageNumber.intValue()){
            throw new NotFoundException(DayControllerDTO.class, "Номер страницы превышает их общее количество");
        }
        return StreamSupport.stream(res.spliterator(), false)
                .map(mapper::dayEntityToDayControllerDTO).toList();
    }
    @Transactional
    public DayControllerDTO addOne(DayControllerDTO DayControllerDTO) {
        return mapper.dayEntityToDayControllerDTO(
                repository.save(mapper.dayControllerDTOToDayEntity(DayControllerDTO)));
    }
    @Transactional
    public DayControllerDTO updateOne(DayControllerDTO DayControllerDTO, Long id) {
        return mapper.dayEntityToDayControllerDTO(repository.findById(id)
                .map(dayEntity -> {
                    dayEntity.setDayName(DayControllerDTO.getDayName());
                    dayEntity.setWeekDayWorkOut(DayControllerDTO.getWeekDayWorkOut());
                    dayEntity.setCalendarEntity(new CalendarEntity(DayControllerDTO
                            .getCalendarControllerDTOId()));
                    return repository.save(dayEntity);
                })
                .orElseGet(() -> repository.save(mapper.dayControllerDTOToDayEntity(DayControllerDTO))));
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
