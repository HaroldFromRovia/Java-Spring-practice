package ru.itis.kpfu.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.itis.kpfu.services.interfaces.ConfirmationService;
import ru.itis.kpfu.services.interfaces.CookieService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@WebFilter("/main")
public class ConfirmedFilter implements Filter {

    private CookieService cookieService;
    private ConfirmationService confirmationService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext context = (ApplicationContext) filterConfig.getServletContext().getAttribute("springContext");
        cookieService = context.getBean(CookieService.class);
        confirmationService = context.getBean(ConfirmationService.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String email;
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        Optional<Cookie> cookie = cookieService.findCookie(req.getCookies(), "email");
        if (cookie.isPresent()) {
            email = cookie.get().getValue();
            if (confirmationService.isConfirmed(email))
                filterChain.doFilter(servletRequest, servletResponse);
            else req.getRequestDispatcher("WEB-INF/jsp/nonConfirmed.jsp").forward(servletRequest, servletResponse);
        } else filterChain.doFilter(servletRequest, servletResponse);
    }
}
