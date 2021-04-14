package ru.itis.kpfu.bentos.springboothomework.security.accessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.bentos.springboothomework.security.accessService.interfaces.AccessService;
import ru.itis.kpfu.bentos.springboothomework.utils.Geolocation;

import javax.servlet.http.HttpServletRequest;

@Component(value = "permissionEvaluator")
public class AccessServiceImpl implements AccessService {

    private final Geolocation geolocation;
    @Autowired
    private HttpServletRequest request;

    public AccessServiceImpl(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    @Override
    public boolean hasAccess() {
        return geolocation.checkRussia(geolocation.getClientIP(request));
    }

}
