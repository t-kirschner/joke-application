package Unittests;

import com.sqs.jokeapplication.JokeApi;
import com.sqs.jokeapplication.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class JokeApiUnitTest {
    @Mock
    private RestTemplate restTemplateMock;

    private JokeApi jokeApi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialisierung der Mock-Objekte

        jokeApi = new JokeApi();
        jokeApi.setRestTemplate(restTemplateMock);
    }

    @Test
    public void testGetApiResponse() {
        // Mock-Verhalten festlegen
        String expectedResponse = "Mocked api response";
        Language language = Language.ENGLISH;
        String url = "https://v2.jokeapi.dev/joke/Programming?" + language.url + "format=txt";

        when(restTemplateMock.getForObject(url, String.class)).thenReturn(expectedResponse);

        // Methode aufrufen
        String actualResponse = jokeApi.getApiResponse(language);

        // Überprüfen
        assertEquals(expectedResponse, actualResponse);
    }
}
