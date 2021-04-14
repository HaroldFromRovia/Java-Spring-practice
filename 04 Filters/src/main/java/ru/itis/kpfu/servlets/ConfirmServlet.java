package ru.itis.kpfu.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.itis.kpfu.services.interfaces.ConfirmationService;
import ru.itis.kpfu.services.interfaces.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registrationConfirm")
public class ConfirmServlet extends HttpServlet {
    @Autowired
    private ConfirmationService confirmationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext context = (ApplicationContext) config.getServletContext().getAttribute("springContext");
        confirmationService = context.getBean(ConfirmationService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if (confirmationService.confirm(token))
            req.getRequestDispatcher("WEB-INF/jsp/confirmed.jsp").forward(req, resp);
        else req.getRequestDispatcher("WEB-INF/jsp/nonConfirmed.jsp").forward(req, resp);
    }
}
