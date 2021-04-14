package ru.itis.kpfu.services.interfaces;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface SmtpService {
    public void sendMessage(String to, String text) throws IOException, TemplateException, MessagingException;
}
