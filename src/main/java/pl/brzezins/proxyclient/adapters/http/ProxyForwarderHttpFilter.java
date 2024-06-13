package pl.brzezins.proxyclient.adapters.http;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class ProxyForwarderHttpFilter extends HttpFilter {
    private final ProxyForwarderHeader proxyForwarderHeader;
    private final ProxyForwarderProperties proxyForwarderProperties;

    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var headerKey = proxyForwarderProperties.getHeaderKey();
        proxyForwarderHeader.setHeader(request.getHeader(headerKey));

        super.doFilter(request, response, chain);
    }
}
