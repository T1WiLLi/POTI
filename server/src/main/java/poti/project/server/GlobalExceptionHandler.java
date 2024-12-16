package poti.project.server;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import poti.project.server.exceptions.ServerException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest request, Model model) {
        HttpStatus status = (ex instanceof ServerException serverEx) ? serverEx.getStatus()
                : HttpStatus.INTERNAL_SERVER_ERROR;
        String message = URLEncoder.encode(ex.getMessage(), StandardCharsets.UTF_8);
        return String.format("/error?HttpStatus=%d&Message=%s&Path=%s", status, message, request.getPathInfo());
    }
}
