package pl.brzezins.proxyclient.adapters.proxy;

import pl.brzezins.proxyclient.domain.Joke;
import pl.brzezins.proxyclient.ports.JokeClient;

public class MockedJokeClient implements JokeClient {
    @Override
    public Joke send() {
        return Joke.builder()
                .id(1)
                .type("QA")
                .setup("Mocked joke")
                .punchline("No joke, it's QA")
                .build();
    }
}
