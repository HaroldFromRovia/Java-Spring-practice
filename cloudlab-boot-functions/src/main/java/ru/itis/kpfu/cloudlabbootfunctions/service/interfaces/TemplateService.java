package ru.itis.kpfu.cloudlabbootfunctions.service.interfaces;

import freemarker.template.TemplateException;
import ru.itis.kpfu.cloudlabbootfunctions.models.Location;
import ru.itis.kpfu.cloudlabbootfunctions.models.Suggestion;
import ru.itis.kpfu.cloudlabbootfunctions.models.SuggestionWrapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TemplateService {

    String fillTemplate(Map<String, List<Suggestion>> data) throws IOException, TemplateException;

    void setTemplateInput(String templateName) throws IOException;

}
