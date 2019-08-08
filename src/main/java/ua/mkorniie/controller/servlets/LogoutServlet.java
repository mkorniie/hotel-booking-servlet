package ua.mkorniie.controller.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LogoutServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html ; charset=UTF-8");

        request.getRequestDispatcher("/").include(request, response);

        HttpSession session=request.getSession();
        session.invalidate();
        logger.info("Session invalidated successfully.");
        logger.info("User logged out successfully.");
    }

}
