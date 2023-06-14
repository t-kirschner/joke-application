package com.sqs.jokeapplication;
import jakarta.annotation.Nullable;
import org.springframework.web.client.RestTemplate;

public class JokeApi {
    private final RestTemplate restTemplate = new RestTemplate();

    @Nullable
    public String getApiResponse(Language language) {
        String url = "https://v2.jokeapi.dev/joke/Programming" + "?" + language.url + "format=txt";

        return restTemplate.getForObject(url, String.class);
    }
}
