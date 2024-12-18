package poti.project.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The {@code HomeController} class handles routing for react applications
 * by forwarding requests to the main {@code index.html} page.
 * This ensures that all routes, except those matching a specific pattern, are
 * handled correctly.
 * 
 * @author William Beaudin
 */
@Controller
public class HomeController {

    /**
     * Handles all requests that do not contain a file extension and forwards them
     * to {@code index.html}.
     * This allows client-side routing to take over in react applications.
     *
     * @param path the path variable representing the requested route, excluding
     *             file extensions.
     * @return a string forwarding to {@code /index.html}.
     */
    @GetMapping(value = "/{path:[^\\.]*}")
    public String home(@PathVariable(required = false) String path) {
        return "forward:/index.html";
    }
}