package logic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Mislav Vuletić
 * @version 1.0 04. Jun 2018.
 * @project portfolio-website
 **/
@Controller
public class SimplePagesController {
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/resume")
    public String resume() {
        return "resume";
    }

    @GetMapping("/sayHi")
    public String sayHi () {
        return "sayHi";
    }
}
