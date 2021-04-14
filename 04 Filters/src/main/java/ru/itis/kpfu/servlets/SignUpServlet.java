package ru.itis.kpfu.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.itis.kpfu.services.interfaces.ConfirmationService;
import ru.itis.kpfu.services.interfaces.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    @Autowired
    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext context = (ApplicationContext) config.getServletContext().getAttribute("springContext");
        signUpService = context.getBean(SignUpService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        signUp(req, resp);
        req.getRequestDispatcher("WEB-INF/jsp/waiting.jsp").forward(req, resp);
    }

    private void signUp(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        signUpService.signUp(email, name, password);
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(60480);
        resp.addCookie(cookie);
    }

}
