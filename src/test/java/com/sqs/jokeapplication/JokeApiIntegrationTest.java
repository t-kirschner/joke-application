package com.sqs.jokeapplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JokeApiIntegrationTest {
    private TestRestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void testGetApiResponse() {
        // check answer from api request

        String urlEnglish = "https://v2.jokeapi.dev/joke/Programming?format=txt";
        String urlGerman = "https://v2.jokeapi.dev/joke/Programming?lang=de&format=txt";

        ResponseEntity<String> responseEnglish = restTemplate.getForEntity(urlEnglish, String.class);
        ResponseEntity<String> responseGerman = restTemplate.getForEntity(urlGerman, String.class);

        assertEquals(HttpStatus.OK, responseEnglish.getStatusCode());
        assertNotNull(responseEnglish.getBody());
        assertEquals(HttpStatus.OK, responseGerman.getStatusCode());
        assertNotNull(responseGerman.getBody());
    }
}
