package com.sqs.jokeapplication;

import java.io.IOException;
import java.util.logging.*;

// Logging occurring errors to logfile.log
public class LoggingManager {
    private static final Logger logger = Logger.getLogger("Joke Application");

    static {
        try {
            FileHandler fileHandler = new FileHandler(System.getProperty("user.dir") + "\\logfile.log", true);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
