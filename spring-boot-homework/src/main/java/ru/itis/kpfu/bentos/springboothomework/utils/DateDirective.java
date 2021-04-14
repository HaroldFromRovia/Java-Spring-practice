package ru.itis.kpfu.bentos.springboothomework.utils;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.*;

/**
 * FreeMarker user-defined directive for repeating a section of a template,
 * optionally with separating the output of the repetations with
 * <tt>&lt;hr></tt>-s.
 *
 *
 * <p><b>Directive info</b></p>
 *
 * <p>Parameters:
 * <ul>
 *   <li><code>count</code>: The number of repetations. Required!
 *       Must be a non-negative number. If it is not a whole number then it will
 *       be rounded <em>down</em>.
 *   <li><code>hr</code>: Tells if a HTML "hr" element could be printed between
 *       repetations. Boolean. Optional, defaults to <code>false</code>.
 * </ul>
 *
 * <p>Loop variables: One, optional. It gives the number of the current
 *    repetation, starting from 1.
 *
 * <p>Nested content: Yes
 */
public class DateDirective implements TemplateDirectiveModel {

    private static final String PARAM_TIME = "time";

    public void execute(Environment env,
                        Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body)
            throws TemplateException, IOException {

        // ---------------------------------------------------------------------
        // Processing the parameters:

        LocalDateTime time = LocalDateTime.now();
        boolean timeParam = false;

        for (Object o : params.entrySet()) {
            Map.Entry ent = (Map.Entry) o;

            String paramName = (String) ent.getKey();
            TemplateModel paramValue = (TemplateModel) ent.getValue();

            if (paramName.equals(PARAM_TIME)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
                time = LocalDateTime.parse(paramValue.toString().replace("T", " ").substring(0, 22), formatter);
            } else {
                throw new TemplateModelException(
                        "Unsupported parameter: " + paramName);
            }
            timeParam = true;
        }
        if (!timeParam) {
            throw new TemplateModelException(
                    "The required \"" + PARAM_TIME + "\" paramter"
                            + "is missing.");
        }

        if (loopVars.length > 1) {
            throw new TemplateModelException(
                    "At most one loop variable is allowed.");
        }

        Writer out = env.getOut();
        long diff = ChronoUnit.MINUTES.between(time, LocalDateTime.now());
        if (diff < 1) {
            out.write("Меньше минуты назад");
        } else if (diff < 5) {
            out.write("Меньше 5 минут назад");
        } else if (diff < 60) {
            out.write("Меньше часа назад");
        } else {
            out.write(time.toString());
        }
    }
}



