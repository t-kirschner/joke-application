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
    private Buffer buffer;

    @PostMapping("/")
    public String getData(@RequestBody String userInput) {

        if (buffer.getCountByLanguage(Set.GERMAN.toString()) < 5) {
            buffer.loadJokes(Set.GERMAN);
        } else if (buffer.getCountByLanguage(Set.ENGLISH.toString()) < 5) {
            buffer.loadJokes(Set.ENGLISH);
        }

        return buffer.getJoke(userInput);
    }
}
//SELECT * FROM JOKES;
//DELETE FROM JOKES WHERE LANGUAGE = 'GERMAN';