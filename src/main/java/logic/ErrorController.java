package logic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mislav Vuletić
 * @version 1.0 03. Jun 2018.
 * @project portfolio-website
 **/
@Controller
public class ErrorController {
    @GetMapping("/error")
    public String initialMessage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "error";
    }
}
