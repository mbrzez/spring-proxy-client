package pl.brzezins.proxyclient.adapters.rest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.brzezins.proxyclient.ports.JokeClient;

@Configuration
public class JokeConfig {
    @Bean
    @ConfigurationProperties("joke.client")
    public JokeProperties jokeProperties() {
        return new JokeProperties();
    }

    @Bean
    public JokeClient restJokeClient(RestTemplate jokeRestTemplate, JokeProperties jokeProperties) {
        return new RestJokeClient(jokeRestTemplate, jokeProperties);
    }

    @Bean
    public RestTemplate jokeRestTemplate() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        return restTemplateBuilder.build();
    }

    // This not makes bean collision until proper bean names are defined in the method signature
    // restJokeClient(RestTemplate jokeRestTemplate, JokeProperties jokeProperties)
    @Bean
    public RestTemplate notUsedRestTemplate() {
        return new RestTemplate();
    }
}
