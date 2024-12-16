package poti.project.server.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ServerException extends RuntimeException {
    @Getter
    private final HttpStatus status;

    public ServerException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
