package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.controller.dto.DayWorkControllerDTO;
import ru.study.webapp.exceptions.NotFoundException;
import ru.study.webapp.repository.entity.DayWorkEntity;
import ru.study.webapp.repository.entity.MonthEntity;
import ru.study.webapp.model.mappers.DatabaseDTOMapper;
import ru.study.webapp.repository.DayWorkRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service

public class DayWorkService {
    private final DayWorkRepository repository;
    private final DatabaseDTOMapper mapper = Mappers.getMapper(DatabaseDTOMapper.class);

    public DayWorkService(DayWorkRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public List<DayWorkControllerDTO> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::dayWorkEntityToDayWorkControllerDTO).toList();
    }

    @Transactional
    public DayWorkControllerDTO getOne(Long id) {
        return mapper.dayWorkEntityToDayWorkControllerDTO(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(DayWorkControllerDTO.class, id)));
    }
    @Transactional
    public List<DayWorkControllerDTO> getOnePage(Long pageSize, Long pageNumber){
        Page<DayWorkEntity> res = repository.findAll(
                PageRequest.of(pageNumber.intValue()-1, pageSize.intValue()));
        if(res.getTotalPages() < pageNumber.intValue()){
            throw new NotFoundException(DayWorkControllerDTO.class, "Номер страницы превышает их общее количество");
        }
        return StreamSupport.stream(res.spliterator(), false)
                .map(mapper::dayWorkEntityToDayWorkControllerDTO).toList();
    }
    @Transactional
    public DayWorkControllerDTO addOne(DayWorkControllerDTO DayWorkControllerDTO) {
        return mapper.dayWorkEntityToDayWorkControllerDTO(
                repository.save(mapper.dayWorkControllerDTOToDayWorkEntity(DayWorkControllerDTO)));
    }
    @Transactional
    public DayWorkControllerDTO updateOne(DayWorkControllerDTO DayWorkControllerDTO, Long id) {
        return mapper.dayWorkEntityToDayWorkControllerDTO(repository.findById(id)
                .map(dayWorkEntity -> {
                    dayWorkEntity.setDateOfWorkDay(DayWorkControllerDTO.getDateOfWorkDay());
                    dayWorkEntity.setMonthEntity(new MonthEntity(
                            DayWorkControllerDTO.getId()));
                    return repository.save(dayWorkEntity);
                })
                .orElseGet(() -> repository.save(mapper.
                        dayWorkControllerDTOToDayWorkEntity(DayWorkControllerDTO))));
    }
    @Transactional
    public Long deleteOne(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(DayWorkControllerDTO.class, id);
        } else {
            repository.deleteById(id);
            return id;
        }
    }
}
