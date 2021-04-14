package ru.itis.kpfu.fileSystem.services.interfaces;

import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

@Service
public interface SmtpService {
    void setTemplateInput(String fileName);
    void sendMessage(String to, Map<String, Object> map) throws IOException, TemplateException, MessagingException;
}
