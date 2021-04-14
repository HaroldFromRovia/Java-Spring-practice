package ru.itis.kpfu.services.interfaces;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.xml.transform.Templates;
import java.io.IOException;

public interface TemplateService {

    String fillEmailTemplate(String data) throws IOException, TemplateException;

    public void setTemplateInput(String inputFile) throws IOException;

}
