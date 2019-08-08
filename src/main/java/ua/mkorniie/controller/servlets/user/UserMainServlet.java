package ua.mkorniie.controller.servlets.user;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import ua.mkorniie.controller.dao.RequestDAO;
import ua.mkorniie.model.enums.RoomClass;
import ua.mkorniie.model.exceptions.DateFormatException;
import ua.mkorniie.model.pojo.DatePair;
import ua.mkorniie.model.pojo.Request;
import ua.mkorniie.model.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/user-main","/new-request"})
public class UserMainServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserMainServlet.class);
    private static RequestDAO requestDAO = new RequestDAO();


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("templates/user/user-main.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/user-main":
                doGet(request, response);
                break;
            case "/new-request":
                makeNewRequest(request);
                request.getRequestDispatcher("/user-main").forward(request, response);
        }

    }

    private void makeNewRequest(@NotNull HttpServletRequest request) {
        logger.info("Retrieving data from request...");

        try {
            int places = Integer.parseInt(request.getParameter("places"));
            RoomClass roomClass = RoomClass.valueOf(request.getParameter("class"));
            List<Date> dates = parseDates(request.getParameter("daterange"));
            System.out.println("Dates: " + dates);

            Request newRequest = new Request((User)request.getSession().getAttribute("user"),
                    places,
                    roomClass,
                    new DatePair(dates.get(0), dates.get(1)),
                    false);
            requestDAO.insert(newRequest);
        } catch (Exception e) {
            logger.error("Impossible to create request object: wrong input format;");
        }
    }

    private List<Date> parseDates(@NotNull String daterange) throws DateFormatException, ParseException {
        List<Date> result = new ArrayList<>();
        String[] dates = daterange.split(" - ");

        if (dates.length != 2) {
            throw new DateFormatException("Error parsind date string: " + daterange);
        }
        DateFormat df = new SimpleDateFormat("mm/dd/yyyy");

        for (String s : dates) {
            result.add(new Date(df.parse(s).getTime()));
        }
        return result;
    }
}
