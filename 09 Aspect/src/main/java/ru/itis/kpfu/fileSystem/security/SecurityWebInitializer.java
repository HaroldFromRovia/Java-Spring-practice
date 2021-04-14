package ru.itis.kpfu.fileSystem.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import ru.itis.kpfu.fileSystem.config.ApplicationContextConfig;

public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebInitializer() {
        super(ApplicationContextConfig.class);
    }

}
