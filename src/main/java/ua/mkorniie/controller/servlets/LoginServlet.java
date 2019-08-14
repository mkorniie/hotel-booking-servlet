package ua.mkorniie.controller.servlets;

import org.apache.log4j.Logger;
import ua.mkorniie.controller.dao.UserDAO;
import ua.mkorniie.controller.util.Localization;
import ua.mkorniie.controller.util.PasswordEncoder;
import ua.mkorniie.controller.util.Paths;
import ua.mkorniie.controller.util.StringConverter;
import ua.mkorniie.model.enums.Role;
import ua.mkorniie.model.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(Paths.LOGIN.getUrl()).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        try {
            login = StringConverter.decodeParameter(request.getParameter("login"));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.selectAll();
        User u = null;
        for (User user : users) {
            if (user.getName().equals(login)) {
                u = user;
                break;
            }
        }
        try {
            if (u == null) {
                request.getRequestDispatcher("templates/user/no-user-found.jsp").forward(request, response);
            }
            else if (u.getPasswordEncoded().equals(PasswordEncoder.getSHA(password))) {
                HttpSession session=request.getSession();
                session.setAttribute("user", u);
                Localization.setCurrentLanguage(u.getLanguage());
                if (u.getRole() == Role.USER) {
                    request.getRequestDispatcher("templates/user/user-main.jsp").forward(request, response);
                } else if (u.getRole() == Role.ADMIN) {
                    request.getRequestDispatcher("admin").forward(request, response);
                }
            }
            request.getRequestDispatcher("templates/login.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
