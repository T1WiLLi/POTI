package poti.project.server.exceptions;

import org.springframework.http.HttpStatus;

public class RateLimitExceededException extends ServerException {
    public RateLimitExceededException(String message) {
        super(HttpStatus.REQUEST_TIMEOUT, message);
    }
}
