package poti.project.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping(value = "/{path:[^\\.]*}")
    public String home(@PathVariable String path) {
        return "forward:/index.html";
    }
}
