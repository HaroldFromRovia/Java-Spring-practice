package ru.itis.kpfu.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.kpfu.services.interfaces.TemplateService;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class FreemarkerTemplateServiceImpl implements TemplateService {

    private Template template;
    private FreeMarkerConfigurer configurer;

    public FreemarkerTemplateServiceImpl(FreeMarkerConfigurer configurer) {
        this.configurer = configurer;
    }

    @Override
    public String fillEmailTemplate(String email) throws IOException, TemplateException {

        Map<String, Object> map = new HashMap<>();
        map.put("confirmationLink", email);
        var writer = new StringWriter();

        template.process(map, writer);
        return writer.toString();
    }

    @Override
    public void setTemplateInput(String inputFile) throws IOException {

        this.template = configurer.getConfiguration().getTemplate(inputFile);

    }
}
