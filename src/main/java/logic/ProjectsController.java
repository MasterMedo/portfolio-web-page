package logic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Mislav Vuletić
 * @version 1.0 03. Jun 2018.
 * @project portfolio-website
 **/
@Controller
public class ProjectsController {
    @GetMapping("/projects")
    public String serveProjects() {
        return "projects";
    }
}
