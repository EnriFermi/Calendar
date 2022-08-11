package ru.study.calendar.errors.errorHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ErrorHandler {
    /**
     * Обрабатывает ошибку, выводя ее сообщение в лог
     * @param err Обрабатываемая ошибка
     */
    static void run(Throwable err) {
        Logger log = LoggerFactory.getLogger(ErrorHandler.class);
        log.error("Error: ", err);
    }
}
