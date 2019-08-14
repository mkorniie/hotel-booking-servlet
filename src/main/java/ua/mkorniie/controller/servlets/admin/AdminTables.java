package ua.mkorniie.controller.servlets.admin;

import com.sun.istack.internal.NotNull;
import ua.mkorniie.controller.dao.*;
import ua.mkorniie.model.enums.RoomClass;
import ua.mkorniie.model.pojo.Room;
import ua.mkorniie.controller.util.Localization;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-update-rooms")
public class AdminTables extends HttpServlet {
    private static RoomDAO roomDAO = new RoomDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/admin-update-rooms":
                try {
                    String pictureURL = request.getParameter("picture");
                    log("Successfully getting pictureURL: " + pictureURL);

                    int places = Integer.parseInt(request.getParameter("places"));
                    log("Successfully getting number of places: " + places);

                    RoomClass roomClass = RoomClass.valueOf(request.getParameter("roomClass"));
                    log("Successfully getting class of rooms: " + roomClass);

                    double price = Double.parseDouble(request.getParameter("price"));
                    log("Successfully getting price of rooms (double value): " + price);

//                    boolean isOccupied = Boolean.getBoolean(request.getParameter("isOccupied"));
//                    log("Successfully getting isOccupied (boolean value): " + isOccupied);


                    boolean isOccupied = false;
                    log("Successfully setting isOccupied (boolean value) to false (by default). ");
                    roomDAO.insert(new Room(places, roomClass, isOccupied, pictureURL, price));
                } catch (Exception e) {
                    log("Unable to create Room object.");
                }

                showPage(request, response);
                break;
            default:
                showNewForm(request, response, "tables.jsp");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Localization.changeLocale(request);
        showNewForm(request, response, "tables.jsp");
    }

    private void showPage(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("bills", new BillDAO().selectAll());
        request.setAttribute("rooms", new RoomDAO().selectAll());
        showNewForm(request, response, "tables.jsp");
    }

    private void showNewForm(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull String filename)
            throws ServletException, IOException {
        String templatePath = "templates/admin/";
        request.getRequestDispatcher(templatePath + filename).forward(request, response);
    }
}
