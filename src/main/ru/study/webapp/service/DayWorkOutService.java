package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.controller.dto.DayWorkOutControllerDTO;
import ru.study.webapp.exceptions.NotFoundException;
import ru.study.webapp.model.database.DayWorkOutDatabaseModel;
import ru.study.webapp.model.database.MonthDatabaseModel;
import ru.study.webapp.model.mappers.DatabaseDTOMapper;
import ru.study.webapp.repository.DayWorkOutRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service

public class DayWorkOutService {
    private final DayWorkOutRepository repository;
    private final DatabaseDTOMapper mapper = Mappers.getMapper(DatabaseDTOMapper.class);

    public DayWorkOutService(DayWorkOutRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public List<DayWorkOutControllerDTO> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::dayWorkOutDatabaseModelToDayWorkOutControllerDTO).toList();
    }

    @Transactional
    public DayWorkOutControllerDTO getOne(Long id) {
        return mapper.dayWorkOutDatabaseModelToDayWorkOutControllerDTO(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(DayWorkOutControllerDTO.class,id)));
    }
    @Transactional
    public List<DayWorkOutControllerDTO> getOnePage(Long pageSize, Long pageNumber){
        Page<DayWorkOutDatabaseModel> res = repository.findAll(
                PageRequest.of(pageNumber.intValue()-1, pageSize.intValue()));
        if(res.getTotalPages() < pageNumber.intValue()){
            throw new NotFoundException(DayWorkOutControllerDTO.class, "Номер страницы превышает их общее количество");
        }
        return StreamSupport.stream(res.spliterator(), false)
                .map(mapper::dayWorkOutDatabaseModelToDayWorkOutControllerDTO).toList();
    }
    @Transactional
    public DayWorkOutControllerDTO addOne(DayWorkOutControllerDTO DayWorkOutControllerDTO) {
        return mapper.dayWorkOutDatabaseModelToDayWorkOutControllerDTO(
                repository.save(mapper.dayWorkOutControllerDTOToDayWorkOutDatabaseModel(DayWorkOutControllerDTO)));
    }
    @Transactional
    public DayWorkOutControllerDTO updateOne(DayWorkOutControllerDTO DayWorkOutControllerDTO, Long id) {
        return mapper.dayWorkOutDatabaseModelToDayWorkOutControllerDTO(repository.findById(id)
                .map(dayWorkOutDatabaseModel -> {
                    dayWorkOutDatabaseModel.setDateOfWorkOutDay(DayWorkOutControllerDTO.getDateOfWorkOutDay());
                    dayWorkOutDatabaseModel.setMonthDatabaseModel(new MonthDatabaseModel(
                            DayWorkOutControllerDTO.getId()));
                    return repository.save(dayWorkOutDatabaseModel);
                })
                .orElseGet(() -> repository.save(mapper.
                        dayWorkOutControllerDTOToDayWorkOutDatabaseModel(DayWorkOutControllerDTO))));
    }
    @Transactional
    public Long deleteOne(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(DayWorkOutControllerDTO.class, id);
        } else {
            repository.deleteById(id);
            return id;
        }
    }
}
