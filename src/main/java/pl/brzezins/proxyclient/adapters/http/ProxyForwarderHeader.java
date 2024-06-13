package pl.brzezins.proxyclient.adapters.http;

import lombok.Data;

@Data
public class ProxyForwarderHeader {
    private String header;

    public boolean forwardIfContains(String headerValue) {
        if (header != null) {
            return header.contains(headerValue);
        }

        return false;
    }
}
