package pl.brzezins.proxyclient.adapters.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.brzezins.proxyclient.adapters.http.ProxyForwarderHeader;
import pl.brzezins.proxyclient.ports.JokeClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest("proxy-client.joke.proxy.enabled=true")
class JokeProxyClientTest {
    @Autowired
    JokeClient jokeClient;

    @MockBean
    ProxyForwarderHeader proxyForwarderHeader;

    @Test
    void should_send_request_to_mock() {
        when(proxyForwarderHeader.forwardIfContains("joke")).thenReturn(true);

        var joke = jokeClient.send();

        System.out.println(joke);

        assertThat(joke.getType()).isEqualTo("QA");
    }

    @Test
    void should_not_send_request_to_mock() {
        when(proxyForwarderHeader.forwardIfContains("joke")).thenReturn(false);

        var joke = jokeClient.send();

        System.out.println(joke);

        assertThat(joke.getType()).isNotEqualTo("QA");
    }
}