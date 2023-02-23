package lk.homies.MailSender.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Helitha Sri
 * @created 2/23/2023 - 3:17 PM
 * @project MailSender
 */

@Getter
@Setter
public class MailDetails {
    @JsonProperty("to_mail")
    private String toMail;
    private String subject;
    private String message;
}
