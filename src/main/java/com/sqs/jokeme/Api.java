package com.sqs.jokeme;
import org.springframework.web.client.RestTemplate;

public class Api {
    private final RestTemplate restTemplate = new RestTemplate();

    public String getApiResponse(String userInput) {
        String url = getUrl(userInput);
        return restTemplate.getForObject(url, String.class);
    }

    private String getUrl(String input) {
        Set inputField1;
        Set inputField2;

        if (input.contains("programming")) {
            inputField1 = Set.PROGRAMMING;
        } else if (input.contains("christmas")) {
            inputField1 = Set.CHRISTMAS;
        } else {
            inputField1 = Set.ANY;
        }

        if (input.contains("german")) {
            inputField2 = Set.GERMAN;
        } else {
            inputField2 = Set.ENGLISH;
        }

        return "https://v2.jokeapi.dev/joke/" + inputField1.option + "?" + inputField2.option + "format=txt";
    }

    enum Set {
        ANY("Any"), PROGRAMMING("Programming"), CHRISTMAS("Christmas"), ENGLISH(""), GERMAN("lang=de&");

        private final String option;

        Set(String option) {
            this.option = option;
        }
    }


}
