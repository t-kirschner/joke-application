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
        // Set mock behavior
        Language language = Language.ENGLISH;
        String response = "Mocked api response";

        when(apiMock.getApiResponse(language)).thenReturn(response);

        // Run test
        boolean result = jokeDatabase.loadJokesFromApi(language);

        // Check
        assertTrue(result);
        verify(repositoryMock, times(1)).saveAll(anyList());
        // Check if the getApiResponse() method was called 10 times
        verify(apiMock, times(10)).getApiResponse(language);
    }


    @Test
    public void testLoadJokesFromApi_Failure() {
        // Set mock behavior
        Language language = Language.GERMAN;

        when(apiMock.getApiResponse(language)).thenReturn(null);

        // Run test
        boolean result = jokeDatabase.loadJokesFromApi(language);

        // Check
        assertFalse(result);
        verify(repositoryMock, never()).saveAll(anyList());
        // Verify that the getApiResponse() method was called
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
