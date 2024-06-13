package pl.brzezins.proxyclient.adapters.proxy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import pl.brzezins.proxyclient.adapters.http.ProxyForwarderConfig;
import pl.brzezins.proxyclient.adapters.http.ProxyForwarderHeader;
import pl.brzezins.proxyclient.adapters.rest.JokeConfig;
import pl.brzezins.proxyclient.ports.JokeClient;

@Configuration
@ConditionalOnBean(ProxyForwarderConfig.class)
@ConditionalOnProperty(name = "proxy-client.joke.mocked-client.enabled", havingValue = "true")
@Import({JokeConfig.class, ProxyForwarderConfig.class})
public class JokeProxyConfig {
    @Bean
    @ConfigurationProperties("proxy-client.joke.mocked-client")
    public JokeProxyProperties jokeProxyProperties() {
        return new JokeProxyProperties();
    }

    @Bean
    public JokeClient mockedJokeClient() {
        return new MockedJokeClient();
    }

    @Bean
    @Primary
    public JokeClient jokeProxyClient(JokeClient mockedJokeClient, JokeClient restJokeClient, JokeProxyProperties proxyProperties, ProxyForwarderHeader forwarderHeader) {
        return new JokeProxyClient(mockedJokeClient, restJokeClient, proxyProperties, forwarderHeader);
    }
}
