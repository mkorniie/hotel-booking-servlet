package ua.mkorniie.controller.servlets;

import ua.mkorniie.controller.dao.UserDAO;
import ua.mkorniie.controller.util.Localization;
import ua.mkorniie.controller.util.PasswordEncoder;
import ua.mkorniie.controller.util.StringConverter;
import ua.mkorniie.model.enums.Role;
import ua.mkorniie.model.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.mkorniie.controller.util.Paths.LOGIN;

//TODO: DB - make 'name' field unique
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("templates/register-form.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User newUser = new User();
        request.setCharacterEncoding("UTF-8");
        newUser.setName(StringConverter.decodeParameter(request.getParameter("name")));
        newUser.setPasswordEncoded(PasswordEncoder.getSHA(request.getParameter("password")));
        newUser.setRole(Role.USER);
        newUser.setEmail(request.getParameter("email"));
        newUser.setLanguage(Localization.getCurrentLanguage());

        UserDAO userDAO = new UserDAO();
        userDAO.insert(newUser);
        request.getRequestDispatcher(LOGIN.getUrl()).forward(request, response);
    }
}
