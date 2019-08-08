package ua.mkorniie.controller.servlets;

import com.mysql.cj.Session;
import ua.mkorniie.model.enums.Role;
import ua.mkorniie.model.pojo.User;
import ua.mkorniie.model.util.Localization;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Role role = null;
        try {
            HttpSession currentSession = request.getSession();
            User currentUser = (User) currentSession.getAttribute("user");
            role = currentUser.getRole();
        } catch (Exception e) {
        }

        request.setAttribute("currentRole", role);
        System.out.println("Entering MainServlet doGet method");
        request.getRequestDispatcher("index.jsp").forward(request, response);
        }
}