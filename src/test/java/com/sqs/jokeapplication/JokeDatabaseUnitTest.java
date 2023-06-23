package com.sqs.jokeapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JokeDatabaseUnitTest {
    @Mock
    private JokeApi apiMock;

    @Mock
    private JokeRepository repositoryMock;

    private JokeDatabase jokeDatabase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        jokeDatabase = new JokeDatabase();
        jokeDatabase.setApi(apiMock);
        jokeDatabase.setRepository(repositoryMock);
    }

    @Test
    public void testLoadJokesFromApi_Success() {
        // Mock-Verhalten festlegen
        Language language = Language.ENGLISH;
        String response = "Mocked api response";

        when(apiMock.getApiResponse(language)).thenReturn(response);

        // Test durchführen
        boolean result = jokeDatabase.loadJokesFromApi(language);

        // Überprüfen
        assertTrue(result);
        verify(repositoryMock, times(1)).saveAll(anyList());
        // Überprüfen, ob die Methode getApiResponse() 10 Mal aufgerufen wurde
        verify(apiMock, times(10)).getApiResponse(language);
    }


    @Test
    public void testLoadJokesFromApi_Failure() {
        // Mock-Verhalten festlegen
        Language language = Language.GERMAN;

        when(apiMock.getApiResponse(language)).thenReturn(null);

        // Test durchführen
        boolean result = jokeDatabase.loadJokesFromApi(language);

        // Überprüfen
        assertFalse(result);
        verify(repositoryMock, never()).saveAll(anyList());
        // Überprüfen, ob die Methode getApiResponse() aufgerufen wurde
        verify(apiMock, times(1)).getApiResponse(language);
    }

    @Test
    public void testGetJokeFromDatabase_WhenJokeExists() {
        // Mocking the repository behavior
        Joke joke = new Joke("Test joke", Language.ENGLISH);
        when(repositoryMock.findFirstByLanguage(Language.ENGLISH.toString())).thenReturn(joke);

        // Calling the method under test
        String result = jokeDatabase.getJokeFromDatabase("1");

        // Verifying the repository interaction
        verify(repositoryMock).delete(joke);

        // Asserting the result
        assertEquals("Test joke", result);
    }

    @Test
    public void testGetJokeFromDatabase_WhenJokeDoesNotExist() {
        // Mocking the repository behavior
        when(repositoryMock.findFirstByLanguage(Language.GERMAN.toString())).thenReturn(null);

        // Calling the method under test
        String result = jokeDatabase.getJokeFromDatabase("0");

        // Verifying the repository interaction
        verify(repositoryMock, never()).delete(any(Joke.class));

        // Asserting the result
        assertNull(result);
    }
}
