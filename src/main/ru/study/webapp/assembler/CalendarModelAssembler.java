package ru.study.webapp.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ru.study.webapp.controller.CalendarController;
import ru.study.webapp.model.database.CalendarDatabaseModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CalendarModelAssembler implements RepresentationModelAssembler<CalendarDatabaseModel, EntityModel<CalendarDatabaseModel>> {

    @Override
    public EntityModel<CalendarDatabaseModel> toModel(CalendarDatabaseModel calendar) {

        return EntityModel.of(calendar,
                linkTo(methodOn(CalendarController.class).one(calendar.getId())).withSelfRel(),
                linkTo(methodOn(CalendarController.class).all()).withRel("calendar"));
    }
}