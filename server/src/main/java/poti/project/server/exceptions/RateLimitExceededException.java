package poti.project.server.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when the rate limit is exceeded.
 */
public class RateLimitExceededException extends ServerException {
    /**
     * Constructs a new RateLimitExceededException with the given message.
     *
     * @param message the detail message
     */
    public RateLimitExceededException(String message) {
        super(HttpStatus.TOO_MANY_REQUESTS, message);
    }
}