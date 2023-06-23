package com.sqs.jokeapplication;

import com.sqs.jokeapplication.Joke;
import com.sqs.jokeapplication.Language;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JokeUnitTest {
    Joke joke = new Joke("Test joke", Language.ENGLISH);

    @Test
    void getJoke() {
        assertEquals("Test joke", joke.getJoke());
    }
}