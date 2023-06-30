package com.sqs.jokeapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        // testing if amount of saved jokes is correct
        ArrayList<Joke> jokeList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Joke jokeGerman = new Joke("Test Witz", Language.GERMAN);
            Joke jokeEnglish= new Joke("Test Joke", Language.ENGLISH);
            jokeList.add(jokeGerman);
            jokeList.add(jokeEnglish);
        }
        jokeRepository.saveAll(jokeList);

        assertEquals(jokeRepository.countByLanguage(Language.GERMAN.toString()), 5);
        assertEquals(jokeRepository.countByLanguage(Language.ENGLISH.toString()), 5);
    }

    @Test
    public void testFindFirstByLanguage() {
        // testing if method returns first joke with set language
        Joke jokeGerman1 = new Joke("Test Witz 1", Language.GERMAN);
        Joke jokeGerman2 = new Joke("Test Witz 2", Language.GERMAN);
        Joke jokeEnglish1 = new Joke("Test Joke 1", Language.ENGLISH);
        Joke jokeEnglish2= new Joke("Test Joke 2", Language.ENGLISH);

        jokeRepository.save(jokeGerman1);
        jokeRepository.save(jokeGerman2);
        jokeRepository.save(jokeEnglish1);
        jokeRepository.save(jokeEnglish2);

        assertEquals(jokeRepository.findFirstByLanguage(Language.GERMAN.toString()), jokeGerman1);
        assertEquals(jokeRepository.findFirstByLanguage(Language.ENGLISH.toString()), jokeEnglish1);
    }
}

