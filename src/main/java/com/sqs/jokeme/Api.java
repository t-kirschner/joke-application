package com.sqs.jokeme;
import org.springframework.web.client.RestTemplate;

public class Api {
    private final RestTemplate restTemplate = new RestTemplate();

    public String getApiResponse(Set set) {
        String url = "https://v2.jokeapi.dev/joke/Programming" + "?" + set.option + "format=txt";

        return restTemplate.getForObject(url, String.class);
    }
}
