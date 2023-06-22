package com.sqs.jokeapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class ControllerTest {
    @Mock
    private JokeDatabase jokeDatabaseMock;

    private Controller controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        controller = new Controller();
        controller.setDatabase(jokeDatabaseMock);
    }

    @Test
    public void testGetData_Success() {
        // Mocking database behavior
        when(jokeDatabaseMock.getCountByLanguage(Language.GERMAN)).thenReturn(5L);
        when(jokeDatabaseMock.getCountByLanguage(Language.ENGLISH)).thenReturn(5L);
        when(jokeDatabaseMock.loadJokesFromApi(Language.GERMAN)).thenReturn(true);
        when(jokeDatabaseMock.loadJokesFromApi(Language.ENGLISH)).thenReturn(true);
        when(jokeDatabaseMock.getJokeFromDatabase(anyString())).thenReturn("Test joke");

        // Creating a mock request body
        String userInput = anyString();

        // Calling the method under test
        ResponseEntity<String> response = controller.getData(userInput);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test joke", response.getBody());

        // Verifying database method invocations
        verify(jokeDatabaseMock, times(1)).getCountByLanguage(Language.GERMAN);
        verify(jokeDatabaseMock, times(1)).getCountByLanguage(Language.ENGLISH);
        verify(jokeDatabaseMock, times(0)).loadJokesFromApi(Language.GERMAN);
        verify(jokeDatabaseMock, times(0)).loadJokesFromApi(Language.ENGLISH);
        verify(jokeDatabaseMock, times(1)).getJokeFromDatabase(userInput);
    }

    @Test
    public void testGetData_JokesNotAvailable() {
        // Mocking database behavior
        when(jokeDatabaseMock.getCountByLanguage(Language.GERMAN)).thenReturn(4L);
        when(jokeDatabaseMock.getCountByLanguage(Language.ENGLISH)).thenReturn(4L);
        when(jokeDatabaseMock.loadJokesFromApi(Language.GERMAN)).thenReturn(false);
        when(jokeDatabaseMock.loadJokesFromApi(Language.ENGLISH)).thenReturn(false);

        // Creating a mock request body
        String userInput = anyString();

        // Calling the method under test
        ResponseEntity<String> response = controller.getData(userInput);

        // Assertions
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals("Sorry, jokes currently not available :/\nPlease check your internet connection and try again later.", response.getBody());

        // Verifying database method invocations
        verify(jokeDatabaseMock, times(1)).getCountByLanguage(Language.GERMAN);
        verify(jokeDatabaseMock, times(0)).getCountByLanguage(Language.ENGLISH);
        verify(jokeDatabaseMock, times(1)).loadJokesFromApi(Language.GERMAN);
        verify(jokeDatabaseMock, times(0)).loadJokesFromApi(Language.ENGLISH);
        verify(jokeDatabaseMock, never()).getJokeFromDatabase(anyString());
    }

    @Test
    public void testGetData_DatabaseNotResponding() {
        // Mocking database behavior
        when(jokeDatabaseMock.getCountByLanguage(Language.GERMAN)).thenReturn(5L);
        when(jokeDatabaseMock.getCountByLanguage(Language.ENGLISH)).thenReturn(5L);
        when(jokeDatabaseMock.loadJokesFromApi(Language.GERMAN)).thenReturn(true);
        when(jokeDatabaseMock.loadJokesFromApi(Language.ENGLISH)).thenReturn(true);
        when(jokeDatabaseMock.getJokeFromDatabase(anyString())).thenReturn(null);

        // Creating a mock request body
        String userInput = anyString();

        // Calling the method under test
        ResponseEntity<String> response = controller.getData(userInput);

        // Assertions
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals("Database doesn't respond as expected. Please restart service and try again.", response.getBody());

        // Verifying database method invocations
        verify(jokeDatabaseMock, times(1)).getCountByLanguage(Language.GERMAN);
        verify(jokeDatabaseMock, times(1)).getCountByLanguage(Language.ENGLISH);
        verify(jokeDatabaseMock, times(0)).loadJokesFromApi(Language.GERMAN);
        verify(jokeDatabaseMock, times(0)).loadJokesFromApi(Language.ENGLISH);
        verify(jokeDatabaseMock, times(1)).getJokeFromDatabase(userInput);
    }
}
