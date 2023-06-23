package com.sqs.jokeapplication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JokeUnitTest {
    Joke joke = new Joke("Test joke", Language.ENGLISH);

    @Test
    void getJoke() {
        assertEquals("Test joke", joke.getJoke());
    }
}