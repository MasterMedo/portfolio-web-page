package logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Mislav Vuletić
 * @version 1.0 04. Jun 2018.
 * @project portfolio-website
 **/
@Controller
public class SimplePagesController {
    private static final Logger logger = LoggerFactory.getLogger(SimplePagesController.class);
    @Autowired
    private NotificationService notificationService;
    private static final String defaultMessage = "Hi,\nMy name is John. I'm a senior developer at CrabYcatS.\nWe are searching for a backend game developer willing to tackle game feel.\n\nEmail me if interested: 1stepAway@CrabY.catS";
    private static byte[] resumeBytes;
    private static final String aboutMe = "Hi,\nI am student at the University of Zagreb, Faculty of Computing and Electrical Engineering.\nI'm currently pursuing a Bachelor's degree in computer science.\nI am an evident extrovert with great communication and adaptation skills.\n I spend my free time creating projects and biking or swimming.\nMy interests include both backend and frontend development.";
    private static final File catalinaBase = new File(System.getProperty("catalina.base")).getAbsoluteFile();
    private static final File resume = new File(catalinaBase, "webapps/ROOT/WEB-INF/classes/static/pdf/resume.pdf");
    static {
        try {
            resumeBytes = Files.readAllBytes(resume.toPath());
        } catch (IOException e) {
            logger.info("Resume could not be loaded from the file system.");
            resumeBytes = null;
        }
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("aboutMe", aboutMe);
        return "about";
    }

    @GetMapping("/resume")
    public ResponseEntity<byte[]> resume() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String name = "resume";

        headers.add("content-disposition", "inline;filename=" + name);

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(resumeBytes, headers, HttpStatus.OK);
        return response;
    }

    @GetMapping("/sayHi")
    public String sayHi(Model model) {
        model.addAttribute("defaultMessage", defaultMessage);
        return "sayHi";
    }

    @GetMapping("/sayHi/resultMessage")
    public String messageResult(@RequestParam(name = "text", required = false, defaultValue = defaultMessage) String text, Model model) {
        String resultMessage = "The mail has been sent successfully!";
        if (text.equals(defaultMessage))
            resultMessage = "You haven't changed the initial message, mate...";
        try {
            notificationService.sendNotification(text);
        } catch (MailException e) {
            logger.info("Mail exception " + e.getMessage());
            resultMessage = "Sending message unfortunately failed.";
        }
        model.addAttribute("resultMessage", resultMessage);
        return "resultMessage";
    }
}
