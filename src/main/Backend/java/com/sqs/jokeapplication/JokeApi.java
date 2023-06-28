package com.sqs.jokeapplication;

import jakarta.annotation.Nullable;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;


// connection to a third party joke api
public class JokeApi {
    private static final Logger logger = LoggingManager.getLogger();
    private RestTemplate restTemplate = new RestTemplate();

    // returns answer from api request
    @Nullable
    public String getApiResponse(Language language) {
        String url = "https://v2.jokeapi.dev/joke/Programming?" + language.url + "format=txt";

        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Joke Api Error", e);
            return null;
        }
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
