package pl.brzezins.proxyclient.adapters.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.brzezins.proxyclient.ports.JokeClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest("joke.client.proxy-mode=false")
class JokeProxyClientNoForwardTest {
    @Autowired
    JokeClient jokeClient;

    @Test
    void should_send_request_to_mock() {
        var joke = jokeClient.send();


        assertThat(joke.getType()).isNotEqualTo("QA");
    }

    @Test
    void should_not_send_request_to_mock() {
        var joke = jokeClient.send();

        assertThat(joke.getType()).isNotEqualTo("QA");
    }
}