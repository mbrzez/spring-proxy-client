package pl.brzezins.proxyclient.adapters.proxy;

import lombok.Data;

@Data
public class JokeProxyProperties {
    private boolean enabled;
    private String proxyHeaderValue;
    private String proxyUrl;
}
