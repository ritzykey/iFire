package com.iFire.webservice.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iFire.webservice.configuration.iFireProperties;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    JavaMailSenderImpl mailSenderImpl;

    @Autowired
    iFireProperties iFireProperties;

    @Autowired
    MessageSource messageSource;

    @PostConstruct
    public void initialize() {

        this.mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(iFireProperties.getEmail().host());
        mailSenderImpl.setPort(iFireProperties.getEmail().port());
        mailSenderImpl.setUsername(iFireProperties.getEmail().username());
        mailSenderImpl.setPassword(iFireProperties.getEmail().password());

        Properties properties = mailSenderImpl.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");

    }

    String activationEmail = """
            <html>
                <body>
                    <h1>${title}</h1>
                    <a href="${url}">${clickHere}</a>
                </body>
            </html>
            """;

    public void sendActivationEmail(String email, String activationToken) {

        String activationURL = iFireProperties.getEmail().clientHost() + "/activation/" + activationToken;
        String title = messageSource.getMessage("iFire.mail.user.created.title", null, LocaleContextHolder.getLocale());
        String clickHere = messageSource.getMessage("iFire.mail.click.here", null, LocaleContextHolder.getLocale());
        String mailBody = activationEmail
                .replace("${url}", activationURL)
                .replace("${title}", title)
                .replace("${clickHere}", clickHere);

        MimeMessage mimeMessage = mailSenderImpl.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            message.setFrom(iFireProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody, true);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.mailSenderImpl.send(mimeMessage);
    }

}
