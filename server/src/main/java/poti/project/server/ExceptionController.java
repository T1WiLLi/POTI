package poti.project.server;

import java.util.Optional;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The {@code ExceptionController} class handles application-wide errors by
 * implementing the {@link ErrorController} interface.
 * It redirects error responses to a dedicated error handling endpoint with
 * relevant details.
 * <p>
 * This controller extracts the HTTP status code and its corresponding message
 * to provide
 * user-friendly error handling and redirection.
 * </p>
 * 
 * @author William Beaudin
 */
@Controller
public class ExceptionController implements ErrorController {

    /**
     * Handles errors occurring in the application by extracting the HTTP status
     * code
     * and reason phrase, then redirects the request to a designated error handling
     * path.
     *
     * @param request  the HTTP request containing error details.
     * @param response the HTTP response object.
     * @return a redirect URL with the HTTP status code and message as query
     *         parameters.
     */
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        int statusCode = Optional.ofNullable(status)
                .map(s -> Integer.parseInt(s.toString()))
                .orElse(500);

        HttpStatus httpStatus = HttpStatus.resolve(statusCode);

        String message = Optional.ofNullable(httpStatus)
                .map(HttpStatus::getReasonPhrase)
                .orElse("Unknown Error");

        return "redirect:/handle-error?HttpStatus=" + statusCode + "&Message=" + message;
    }

    /**
     * Provides the path for the error endpoint.
     * This method is required when implementing {@link ErrorController}.
     *
     * @return the path {@code /error} for error handling.
     */
    public String getErrorPath() {
        return "/error";
    }
}
