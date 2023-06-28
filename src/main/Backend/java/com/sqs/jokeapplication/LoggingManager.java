package com.sqs.jokeapplication;

import java.io.IOException;
import java.util.logging.*;

// Logging occurring errors to logfile.log
public class LoggingManager {
    private static final Logger logger = Logger.getLogger("Joke Application");
    private static final FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler("C:/Users/vonto/IdeaProjects/joke-application/src/main/Backend/java/logfile.log", true);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
