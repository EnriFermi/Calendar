package ru.study.calendar.config.parsers.impl.jdbc;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServerConfiguration {
    private String userName;
    private String password;
    private String connectionURL;
    private String driverName;
}
