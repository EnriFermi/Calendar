package ru.study.webapp.model.configuration.services.connectors.db.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServerConnectionConfiguration {
    private String userName;
    private String password;
    private String connectionURL;
    private String driverName;
    private Integer calendarId;
}
