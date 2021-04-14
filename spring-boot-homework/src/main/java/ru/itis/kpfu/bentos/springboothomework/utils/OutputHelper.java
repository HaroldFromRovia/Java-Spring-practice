package ru.itis.kpfu.bentos.springboothomework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OutputHelper {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static final String INFO = ANSI_GREEN + "  INFO" + ANSI_RESET;
    public static final String ERROR = ANSI_RED + "  ERROR" + ANSI_RESET;
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
}
