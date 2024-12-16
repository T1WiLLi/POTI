package poti.project.server.components;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

@Component
public class RateLimitingFilter implements Filter {

    private static final long REQUEST_LIMIT = 100;
    private static final long TIME_WINDOW = TimeUnit.MINUTES.toMillis(1);

    private final Map<String, ClientRequestInfo> requestCounts = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest && response instanceof HttpServletResponse httpResponse) {
            String requestUri = httpRequest.getRequestURI();

            if (requestUri.startsWith("/api/")) {
                chain.doFilter(request, response);
                return;
            }

            String clientIp = httpRequest.getRemoteAddr();

            if (isRateLimitExceeded(clientIp)) {
                int status = HttpServletResponse.SC_REQUEST_TIMEOUT;
                String message = URLEncoder.encode("Too many requests. Please try again later.",
                        StandardCharsets.UTF_8);
                String path = URLEncoder.encode(requestUri, StandardCharsets.UTF_8);

                String redirectUrl = String.format("/error?HttpStatus=%d&Message=%s&Path=%s", status, message, path);
                httpResponse.sendRedirect(redirectUrl);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private boolean isRateLimitExceeded(String clientIp) {
        long currentTime = Instant.now().toEpochMilli();
        ClientRequestInfo requestInfo = requestCounts.computeIfAbsent(clientIp,
                k -> new ClientRequestInfo(currentTime));

        synchronized (requestInfo) {
            if (currentTime - requestInfo.getStartTime() > TIME_WINDOW) {
                requestInfo.setStartTime(currentTime);
                requestInfo.setRequestCount(0);
            }

            if (requestInfo.getRequestCount() < REQUEST_LIMIT) {
                requestInfo.setRequestCount(requestInfo.getRequestCount() + 1);
                return false;
            } else {
                return true;
            }
        }
    }

    @Getter
    @Setter
    private static class ClientRequestInfo {
        private long startTime;
        private long requestCount;

        private ClientRequestInfo(long startTime) {
            this.startTime = startTime;
            this.requestCount = 0;
        }
    }
}
