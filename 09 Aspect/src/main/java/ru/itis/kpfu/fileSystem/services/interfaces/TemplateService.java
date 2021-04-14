package ru.itis.kpfu.fileSystem.services.interfaces;

import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public interface TemplateService {

    String fillEmailTemplate(Map<String, Object> data) throws IOException, TemplateException;

    public void setTemplateInput(String inputFile) throws IOException;

}
