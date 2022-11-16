package ru.study.webapp.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.webapp.controller.dto.YearControllerDTO;
import ru.study.webapp.exceptions.NotFoundException;
import ru.study.webapp.model.database.CalendarDatabaseModel;
import ru.study.webapp.model.database.YearDatabaseModel;
import ru.study.webapp.model.mappers.DatabaseDTOMapper;
import ru.study.webapp.repository.YearRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service

public class YearService {
    private final YearRepository repository;
    private final DatabaseDTOMapper mapper = Mappers.getMapper(DatabaseDTOMapper.class);

    public YearService(YearRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public List<YearControllerDTO> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::yearDatabaseModelToYearControllerDTO).toList();
    }

    @Transactional
    public YearControllerDTO getOne(Long id) {
        return mapper.yearDatabaseModelToYearControllerDTO(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(YearControllerDTO.class,id)));
    }
    @Transactional
    public List<YearControllerDTO> getOnePage(Long pageSize, Long pageNumber){
        Page<YearDatabaseModel> res = repository.findAll(
                PageRequest.of(pageNumber.intValue()-1, pageSize.intValue()));
        if(res.getTotalPages() < pageNumber.intValue()){
            throw new NotFoundException(YearControllerDTO.class, "Номер страницы превышает их общее количество");
        }
        return StreamSupport.stream(res.spliterator(), false)
                .map(mapper::yearDatabaseModelToYearControllerDTO).toList();
    }
    @Transactional
    public YearControllerDTO addOne(YearControllerDTO YearControllerDTO) {
        return mapper.yearDatabaseModelToYearControllerDTO(
                repository.save(mapper.yearControllerDTOToYearDatabaseModel(YearControllerDTO)));
    }
    @Transactional
    public YearControllerDTO updateOne(YearControllerDTO YearControllerDTO, Long id) {
        return mapper.yearDatabaseModelToYearControllerDTO(repository.findById(id)
                .map(YearDatabaseModel -> {
                    YearDatabaseModel.setCalendarDatabaseModel(new CalendarDatabaseModel(
                            YearControllerDTO.getCalendarControllerDTOId()));
                    return repository.save(YearDatabaseModel);
                })
                .orElseGet(() -> repository.save(mapper.yearControllerDTOToYearDatabaseModel(YearControllerDTO))));
    }
    @Transactional
    public Long deleteOne(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(YearControllerDTO.class, id);
        } else {
            repository.deleteById(id);
            return id;
        }
    }
}
