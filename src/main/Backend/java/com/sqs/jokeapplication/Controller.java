package com.sqs.jokeapplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:5173/")
@Component
public class Controller {

    @Autowired
    private JokeDatabase database;

    @PostMapping("/")
    public String getData(@RequestBody String userInput) {

        if (database.getCountByLanguage(Language.GERMAN.toString()) < 5) {
            database.loadJokes(Language.GERMAN);
        } else if (database.getCountByLanguage(Language.ENGLISH.toString()) < 5) {
            database.loadJokes(Language.ENGLISH);
        }

        return database.getJoke(userInput);
    }
}
//SELECT * FROM JOKES;
//DELETE FROM JOKES WHERE LANGUAGE = 'GERMAN';