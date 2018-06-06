package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Mislav Vuletić
 * @version 1.0 04. Jun 2018.
 * @project portofolio-website
 **/
@Service
public class NotificationService {
    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(String text) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("mastermmedo@gmail.com");
        mail.setFrom("mastermmedo@gmail.com");
        mail.setSubject("mmedo.me mail");
        mail.setText(text);

        javaMailSender.send(mail);
    }
}
