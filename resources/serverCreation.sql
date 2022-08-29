create TABLE calendarlist
(
    calendarid       MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    beginnigyear     MediumInt,
    endYear          MediumInt,
    anchorWeekDayKey MediumInt
);

create TABLE YEARLIST
(
    yearId     MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    calendarid MediumINT NOT NULL,
    FOREIGN KEY (calendarid) references calendarlist (calendarid)
);
create TABLE MONTHLIST
(
    monthId   MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    yearId    MEDIUMINT NOT NULL,
    monthName VARCHAR(255),
    dayCount  MEDIUMINT,
    FOREIGN KEY (yearid) references yearlist (yearid)
);
create table dayWorkOutList
(
    dayid            MEDIUMINT NOT NULL AUTO_INCREMENT Primary key,
    monthId          MEDIUMINT NOT NULL REferences monthlist (yearid),
    dateOfWorkOutDay MEDIUMINT,
    FOREIGN KEY (monthid) references monthlist (monthid)
);
create table dayWorkList
(
    dayid         MEDIUMINT NOT NULL AUTO_INCREMENT Primary key,
    monthId       MEDIUMINT NOT NULL,
    dateOfWorkDay MEDIUMINT,
    FOREIGN KEY (monthid) references monthlist (monthid)
);
create table dayList
(
    dayid          MEDIUMINT NOT NULL AUTO_INCREMENT Primary key,
    calendarid     Mediumint NOT NULL,
    dayName        VARchar(255),
    weekdayworkout BOOLean,
    FOREIGN KEY (calendarid) references calendarlist (calendarid)
);