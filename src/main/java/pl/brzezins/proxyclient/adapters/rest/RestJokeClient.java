package pl.brzezins.proxyclient.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestOperations;
import pl.brzezins.proxyclient.domain.Joke;
import pl.brzezins.proxyclient.ports.JokeClient;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
public class RestJokeClient implements JokeClient {
    private final RestOperations restTemplate;
    private final JokeProperties jokeProperties;

    @Override
    public Joke send() {
        try {
            return restTemplate.getForEntity(new URI(jokeProperties.getUrl()), Joke.class).getBody();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
