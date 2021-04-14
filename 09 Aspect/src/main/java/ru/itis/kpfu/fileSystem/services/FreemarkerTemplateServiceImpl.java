package ru.itis.kpfu.fileSystem.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.kpfu.fileSystem.services.interfaces.TemplateService;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class FreemarkerTemplateServiceImpl implements TemplateService {

    private Template template;
    private FreeMarkerConfigurer configurer;

    public FreemarkerTemplateServiceImpl(FreeMarkerConfigurer configurer) {
        this.configurer = configurer;
    }

    @Override
    public String fillEmailTemplate(Map<String, Object> map) throws IOException, TemplateException {
        var writer = new StringWriter();
        template.process(map, writer);
        return writer.toString();
    }

    @Override
    public void setTemplateInput(String inputFile) throws IOException {
        this.template = configurer.getConfiguration().getTemplate(inputFile);
    }

}
