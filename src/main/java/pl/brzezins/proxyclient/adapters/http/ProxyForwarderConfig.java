package pl.brzezins.proxyclient.adapters.http;

import jakarta.servlet.Filter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
@ConditionalOnProperty(name = "proxy-client.proxy.enabled", havingValue = "true")
public class ProxyForwarderConfig {
    @Bean
    @ConfigurationProperties("proxy-client.proxy")
    public ProxyForwarderProperties forwarderProperties() {
        return new ProxyForwarderProperties();
    }

    @Bean
    @RequestScope
    public ProxyForwarderHeader proxyForwarderHeader() {
        return new ProxyForwarderHeader();
    }

    @Bean
    public Filter proxyForwaredHttpFilter(ProxyForwarderHeader proxyForwarderHeader, ProxyForwarderProperties forwarderProperties) {
        return new ProxyForwarderHttpFilter(proxyForwarderHeader, forwarderProperties);
    }
}
