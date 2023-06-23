package com.sqs.jokeapplication;

import com.sqs.jokeapplication.Joke;
import com.sqs.jokeapplication.JokeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JokeDatabaseIntegrationTest {

    @Autowired
    private JokeRepository jokeRepository;

    @Test
    public void saveJokeAndRetrieveById() {
        // Arrange
        Joke joke = new Joke("Test Joke", Language.ENGLISH);

        // Act
        jokeRepository.save(joke);
        Joke retrievedJoke = jokeRepository.findById(joke.getId()).orElse(null);

        // Assert
        assertThat(retrievedJoke).isNotNull();
        assertThat(retrievedJoke.getJoke()).isEqualTo(joke.getJoke());
    }

    @Test
    public void deleteJoke() {
        // Arrange
        Joke joke = new Joke("Test Joke", Language.GERMAN);

        // Act
        jokeRepository.save(joke);
        Joke retrievedJoke = jokeRepository.findById(joke.getId()).orElse(null);

        // Assert
        assertThat(retrievedJoke).isNotNull();
        assertThat(retrievedJoke.getJoke()).isEqualTo(joke.getJoke());

        jokeRepository.delete(joke);
        assertThat(jokeRepository.findById(joke.getId())).isEmpty();
    }

    @Test
    public void testCountByLanguage() {

    }

    @Test
    public void testFindFirstByLanguage() {

    }
}

