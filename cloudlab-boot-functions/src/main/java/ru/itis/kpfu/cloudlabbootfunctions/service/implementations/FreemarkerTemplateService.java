package ru.itis.kpfu.cloudlabbootfunctions.service.implementations;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.kpfu.cloudlabbootfunctions.models.Location;
import ru.itis.kpfu.cloudlabbootfunctions.models.Suggestion;
import ru.itis.kpfu.cloudlabbootfunctions.service.interfaces.TemplateService;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@Service
public class FreemarkerTemplateService implements TemplateService {

    private Template template;
    private final FreeMarkerConfigurer configurer;

    public FreemarkerTemplateService(@Qualifier("freemarkerConfig") FreeMarkerConfigurer configurer) {
        this.configurer = configurer;
    }

//    @Override
//    public String fillEmailTemplate(Map<String, SuggestionWrapper> map) throws IOException, TemplateException {
//        StringWriter writer = new StringWriter();
//        template.process(map, writer);
//        return writer.toString();
//    }

    @Override
    public String fillTemplate(Map<String, List<Suggestion>> data) throws IOException, TemplateException {
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        return writer.toString();
    }

    @Override
    public void setTemplateInput(String templateName) throws IOException {
        this.template = configurer.getConfiguration().getTemplate(templateName);
    }

}
