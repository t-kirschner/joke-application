package com.sqs.jokeapplication;
import jakarta.annotation.Nullable;
import org.springframework.web.client.RestTemplate;

public class Api {
    private final RestTemplate restTemplate = new RestTemplate();

    @Nullable
    public String getApiResponse(Set set) {
        String url = "https://v2.jokeapi.dev/joke/Programming" + "?" + set.url + "format=txt";

        return restTemplate.getForObject(url, String.class);
    }
}
