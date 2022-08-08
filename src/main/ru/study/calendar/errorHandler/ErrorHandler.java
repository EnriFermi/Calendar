package ru.study.calendar.errorHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public interface ErrorHandler {
    /**
     * Обрабатывает ошибку, выводя ее сообщение в лог
     *
     * @param err Обрабатываемая ошибка
     */
    static void run(Throwable err) {
        Logger log = LoggerFactory.getLogger(ErrorHandler.class);
        log.error("Exception {}\n{}", err.toString(), Arrays.stream(err.getStackTrace())
                                                            .map(object -> object.toString())
                                                            .reduce("", (left, right) -> left + "\tat " + right + "\n"));


        //TODO check?
        log.error("bla bla", err);
    }
}
