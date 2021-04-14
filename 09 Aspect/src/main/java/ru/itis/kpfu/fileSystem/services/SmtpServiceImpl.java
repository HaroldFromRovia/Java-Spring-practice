package ru.itis.kpfu.fileSystem.services;

import freemarker.template.TemplateException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.fileSystem.services.interfaces.SmtpService;
import ru.itis.kpfu.fileSystem.services.interfaces.TemplateService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.util.Map;

@Service
public class SmtpServiceImpl implements SmtpService {

    private final String from = "JavaSmtp";
    private final TemplateService templateService;
    private final JavaMailSender emailSender;

    public SmtpServiceImpl(TemplateService templateService, JavaMailSender emailSender) {
        this.templateService = templateService;
        this.emailSender = emailSender;
    }

    @Override
    public void sendMessage(String to, Map<String, Object> map) throws IOException, TemplateException, MessagingException {

        var message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        message.setRecipient(Message.RecipientType.TO, InternetAddress.parse(to)[0]);
        helper.setFrom(new InternetAddress(from));

        helper.setText(templateService.fillEmailTemplate(map), true);

        emailSender.send(message);
    }

    @Override
    public void setTemplateInput(String fileName) {
        try {
            templateService.setTemplateInput(fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException("Wrong template name");
        }
    }
}
