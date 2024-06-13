package pl.brzezins.proxyclient.adapters.proxy;

import lombok.RequiredArgsConstructor;
import pl.brzezins.proxyclient.adapters.http.ProxyForwarderHeader;
import pl.brzezins.proxyclient.domain.Joke;
import pl.brzezins.proxyclient.ports.JokeClient;

@RequiredArgsConstructor
public class JokeProxyClient implements JokeClient {
    private final JokeClient mockedJokeClient;
    private final JokeClient jokeClient;
    private final JokeProxyProperties jokeProxyProperties;
    private final ProxyForwarderHeader proxyForwarderHeader;

    @Override
    public Joke send() {
        var proxyHeaderName = jokeProxyProperties.getProxyHeaderValue();
        if (proxyForwarderHeader.forwardIfContains(proxyHeaderName)) {
            return mockedJokeClient.send();
        }

        return jokeClient.send();
    }
}
