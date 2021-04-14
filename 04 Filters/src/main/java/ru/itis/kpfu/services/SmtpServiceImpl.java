package ru.itis.kpfu.services;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.services.interfaces.SmtpService;
import ru.itis.kpfu.services.interfaces.TemplateService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

@Service
public class SmtpServiceImpl implements SmtpService {

    private final String from = "j5mtp@yandex.ru";
    private final TemplateService templateService;
    private final JavaMailSender emailSender;

    public SmtpServiceImpl(TemplateService templateService, JavaMailSender emailSender) throws IOException {

        this.templateService = templateService;
        this.templateService.setTemplateInput("confirm_tmp.ftl");

        this.emailSender = emailSender;
    }

    @Override
    public void sendMessage(String to, String text) throws IOException, TemplateException, MessagingException {

        var message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        message.setRecipient(Message.RecipientType.TO, InternetAddress.parse(to)[0]);
        helper.setFrom(new InternetAddress(from));

        helper.setText(templateService.fillEmailTemplate(text), true);

        emailSender.send(message);
    }

}
