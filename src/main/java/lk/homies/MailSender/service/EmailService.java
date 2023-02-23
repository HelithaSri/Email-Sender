package lk.homies.MailSender.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lk.homies.MailSender.models.MailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Helitha Sri
 * @created 2/23/2023 - 4:23 PM
 * @project MailSender
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration configuration;

    public String sendMailWithTemplate(MailDetails mailDetails, Map<String,Object> model){
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Template t = configuration.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(mailDetails.getToMail());
            helper.setText(html, true);
            helper.setSubject(mailDetails.getSubject());
            helper.setFrom("helitha.sri1@gmail.com");
            javaMailSender.send(message);

            return "Done";

        } catch (MessagingException | IOException | TemplateException e) {
            System.out.println(e.getStackTrace());
            return e.getMessage();
        }

    }

}
