package poti.project.server.components;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {
    private static final long MAX_REQUESTS_PER_MINUTES = 100;
    private static final long TIME_FRAME_MS = TimeUnit.MINUTES.toMillis(1);
    private final ConcurrentHashMap<String, RateLimit> clientRateLimits = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String clientIp = request.getRemoteAddr();
        RateLimit rateLimit = clientRateLimits.computeIfAbsent(clientIp, k -> new RateLimit());

        synchronized (rateLimit) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - rateLimit.lastRequestTime > TIME_FRAME_MS) {
                rateLimit.lastRequestTime = currentTime;
                rateLimit.requestCount = 1;
            } else {
                rateLimit.requestCount++;
                if (rateLimit.requestCount > MAX_REQUESTS_PER_MINUTES) {
                    response.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT, "Too many requests");
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private static class RateLimit {
        long lastRequestTime;
        int requestCount;
    }
}
