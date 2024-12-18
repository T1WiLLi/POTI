package poti.project.server.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Base exception class for server-side errors.
 * 
 * @author William Beaudin
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@ToString
public class ServerException extends RuntimeException {
    /**
     * HTTP status associated with the exception.
     */
    @Getter
    private final HttpStatus status;

    /**
     * Constructs a new ServerException with the given HTTP status and error
     * message.
     *
     * @param status  HTTP status
     * @param message error message
     */
    public ServerException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * Constructs a new ServerException with the given HTTP status, error message,
     * and cause.
     *
     * @param status  HTTP status
     * @param message error message
     * @param cause   underlying cause
     */
    public ServerException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
}