package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.controller.dto.MonthControllerDTO;
import ru.study.webapp.exceptions.NotFoundException;
import ru.study.webapp.model.database.MonthDatabaseModel;
import ru.study.webapp.model.database.YearDatabaseModel;
import ru.study.webapp.model.mappers.DatabaseDTOMapper;
import ru.study.webapp.repository.MonthRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service

public class MonthService {
    private final MonthRepository repository;
    private final DatabaseDTOMapper mapper = Mappers.getMapper(DatabaseDTOMapper.class);

    public MonthService(MonthRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public List<MonthControllerDTO> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::monthDatabaseModelToMonthControllerDTO).toList();
    }

    @Transactional
    public MonthControllerDTO getOne(Long id) {
        return mapper.monthDatabaseModelToMonthControllerDTO(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(MonthControllerDTO.class, id)));
    }
    @Transactional
    public List<MonthControllerDTO> getOnePage(Long pageSize, Long pageNumber){
        Page<MonthDatabaseModel> res = repository.findAll(
                PageRequest.of(pageNumber.intValue()-1, pageSize.intValue()));
        if(res.getTotalPages() < pageNumber.intValue()){
            throw new NotFoundException(MonthControllerDTO.class, "Номер страницы превышает их общее количество");
        }
        return StreamSupport.stream(res.spliterator(), false)
                .map(mapper::monthDatabaseModelToMonthControllerDTO).toList();
    }
    @Transactional
    public MonthControllerDTO addOne(MonthControllerDTO MonthControllerDTO) {
        return mapper.monthDatabaseModelToMonthControllerDTO(
                repository.save(mapper.monthControllerDTOToMonthDatabaseModel(MonthControllerDTO)));
    }
    @Transactional
    public MonthControllerDTO updateOne(MonthControllerDTO MonthControllerDTO, Long id) {
        return mapper.monthDatabaseModelToMonthControllerDTO(repository.findById(id)
                .map(MonthDatabaseModel -> {
                    MonthDatabaseModel.setName(MonthControllerDTO.getName());
                    MonthDatabaseModel.setDayCount(MonthControllerDTO.getDayCount());
                    MonthDatabaseModel.setYearDatabaseModel(new YearDatabaseModel(MonthControllerDTO
                            .getYearControllerDTOId()));
                    return repository.save(MonthDatabaseModel);
                })
                .orElseGet(() -> repository.save(mapper.monthControllerDTOToMonthDatabaseModel(MonthControllerDTO))));
    }
    @Transactional
    public Long deleteOne(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(MonthControllerDTO.class, id);
        } else {
            repository.deleteById(id);
            return id;
        }
    }
}

