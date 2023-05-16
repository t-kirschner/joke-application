package com.sqs.jokeme;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:5173/")
public class Controller {
    Api api = new Api();

    @Autowired
    private JokeRepository repository;

    @PostMapping("/")
    public String insertData(@RequestBody String data) {
        Joke joke = new Joke();

        try {
            if(data.contains("german")) {
                joke.setLanguage("german");
                joke.setJoke(api.getApiResponse(Set.GERMAN));

            } else {
                joke.setLanguage("english");
                joke.setJoke(api.getApiResponse(Set.ENGLISH));
            }

            repository.save(joke);

        } catch (Exception e) {
            return "Excuse me, your jokes are too heavy for my database...";
        }

        return "Added joke to database";
    }

    /*
    Api api = new Api();

    @PostMapping("/")
    public String processData(@RequestBody String data) {
        return api.getApiResponse(data);
    }

     */
}
