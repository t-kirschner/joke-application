package com.sqs.jokeapplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> getData(@RequestBody String userInput) {
        if (database.getCountByLanguage(Language.GERMAN) < 5 && !database.loadJokesFromApi(Language.GERMAN)) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Sorry, jokes currently not available :/\nPlease check your internet connection and try again later.");
        }
        if (database.getCountByLanguage(Language.ENGLISH) < 5 && !database.loadJokesFromApi(Language.ENGLISH)) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Sorry, jokes currently not available :/\nPlease check your internet connection and try again later.");
        }

        String joke = database.getJokeFromDatabase(userInput);
        if (joke == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Database doesn't respond as expected. Please restart service and try again.");
        } else {
            return ResponseEntity.ok(joke);
        }
    }

    // on get request
    @GetMapping
    public String getOnlineStatus() {
        return "Joke Application online!";
    }

    public void setDatabase(JokeDatabase database) {
        this.database = database;
    }
}