package pl.brzezins.proxyclient.ports;

import pl.brzezins.proxyclient.domain.Joke;

public interface JokeClient {
    Joke send();
}
