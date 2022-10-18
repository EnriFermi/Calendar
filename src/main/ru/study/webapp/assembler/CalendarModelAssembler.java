package ru.study.webapp.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ru.study.webapp.controllers.CalendarsController;
import ru.study.webapp.database.CalendarDAO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CalendarModelAssembler implements RepresentationModelAssembler<CalendarDAO, EntityModel<CalendarDAO>> {

    @Override
    public EntityModel<CalendarDAO> toModel(CalendarDAO calendar) {

        return EntityModel.of(calendar,
                linkTo(methodOn(CalendarsController.class).one(calendar.getId())).withSelfRel(),
                linkTo(methodOn(CalendarsController.class).all()).withRel("calendar"));
    }
}