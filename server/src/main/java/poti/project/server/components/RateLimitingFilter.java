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

/**
 * 
 * The filter is used to handle Ddos attacks. It checks if the request is coming
 * from a known ip address. If it is, it will then add the ip address to a MAP
 * to then compare it to the current request later.
 * 
 * @apiNote This filter is used to handle therequests to the server. It checks
 *          if the request has been spamming the server, it does not prevent the
 *          request from the frontend itself.
 * @author William Beaudin
 */
@Component
public class RateLimitingFilter extends OncePerRequestFilter {
    private static final long MAX_REQUESTS_PER_MINUTES = 100;
    private static final long TIME_FRAME_MS = TimeUnit.MINUTES.toMillis(1);
    private final ConcurrentHashMap<String, RateLimit> clientRateLimits = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (isExcludedPath(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        String clientIp = request.getRemoteAddr();
        RateLimit rateLimit = clientRateLimits.computeIfAbsent(clientIp, k -> new RateLimit());

        synchronized (rateLimit) {
            long currentTime = System.currentTimeMillis();
            if (isTimeFrameExpired(rateLimit, currentTime)) {
                resetRateLimit(rateLimit, currentTime);
            } else {
                rateLimit.requestCount++;
                if (isRateLimitExceeded(rateLimit)) {
                    response.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT, "Too many requests");
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isExcludedPath(String path) {
        return path.contains("/error") || path.contains("/handle-error");
    }

    private boolean isTimeFrameExpired(RateLimit rateLimit, long currentTime) {
        return currentTime - rateLimit.lastRequestTime > TIME_FRAME_MS;
    }

    private void resetRateLimit(RateLimit rateLimit, long currentTime) {
        rateLimit.lastRequestTime = currentTime;
        rateLimit.requestCount = 1;
    }

    private boolean isRateLimitExceeded(RateLimit rateLimit) {
        return rateLimit.requestCount > MAX_REQUESTS_PER_MINUTES;
    }

    private static class RateLimit {
        long lastRequestTime;
        int requestCount;
    }
}