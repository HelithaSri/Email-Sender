package lk.homies.MailSender.controller;

import lk.homies.MailSender.models.MailDetails;
import lk.homies.MailSender.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Helitha Sri
 * @created 2/23/2023 - 2:56 PM
 * @project MailSender
 */

@RestController
@RequestMapping("api/mail/")
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailService;

    @PostMapping("send")
    public String sendMail(@RequestBody MailDetails mailDetails){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("helitha.emoney@gmail.com");
            message.setTo(mailDetails.getToMail());
            message.setSubject(mailDetails.getSubject());
            message.setText(mailDetails.getMessage());

            javaMailSender.send(message);
            System.out.println("done");
            return "Mail Send!";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PostMapping("send/with-template")
    public String sendMailTemplate(@RequestBody MailDetails mailDetails){

        Random random = new Random();
        int otp = random.nextInt(10000);
        String formattedOtp = String.format("%04d", otp);

        Map<String, Object> model = new HashMap<>();
        model.put("Name", "Helitha Sri");
        model.put("Otp", formattedOtp);
        model.put("BusinessName", "HomiesLk");
        model.put("BusinessMobile", "076xxxxxxx");
        return emailService.sendMailWithTemplate(mailDetails, model);
    }

}
