package com.sqs.jokeapplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

// controls interactions between frontend, api and database
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:5173/")
@Component
public class Controller {

    @Autowired
    private JokeDatabase database;

    // triggered when getting post request from frontend
    @PostMapping("/")
    private String getData(@RequestBody String userInput) {
        if (database.getCountByLanguage(Language.GERMAN) < 5 && !database.loadJokesFromApi(Language.GERMAN)) {
            return "Jokes currently not available :/\nPlease check your internet connection and try again later.";
        }
        if (database.getCountByLanguage(Language.ENGLISH) < 5 && !database.loadJokesFromApi(Language.ENGLISH)) {
            return "Jokes currently not available :/\nPlease check your internet connection and try again later.";
        }

        return Objects.requireNonNullElse(database.getJokeFromDatabase(userInput), "Connection to database failed. Please restart service and try again.");
    }
}