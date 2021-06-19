package niss.log4j.logger.impl;

import niss.log4j.helper.LogFormatter;
import niss.log4j.helper.LogLevel;
import niss.log4j.logger.ILogger;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SpecificLogger implements ILogger {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[32m";

    @Override
    public void debug(String msg) {
        log(msg, LogLevel.DEBUG);
    }

    @Override
    public void info(String msg) {
        log(msg, LogLevel.INFO);
    }

    @Override
    public void warn(String msg) {
        log(msg, LogLevel.WARN);
    }

    @Override
    public void error(String msg) {
        log(msg, LogLevel.ERROR);
    }

    private void log(String msg, LogLevel logLevel) {
        String formatter = LogFormatter.getLogFormatter();
        String dateTime = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:SS").format(LocalDateTime.now());

        String color;
        switch (logLevel) {
            case DEBUG:
                color = ANSI_YELLOW;
                break;
            case WARN:
                color = ANSI_PURPLE;
                break;
            case ERROR:
                color = ANSI_RED;
                break;
            default:
                color = "";
        }

        System.out.println(color + MessageFormat.format(formatter, dateTime, logLevel, msg) + ANSI_RESET);
    }
}
