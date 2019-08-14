package ua.mkorniie.controller.servlets.admin;

import com.sun.istack.internal.NotNull;
import ua.mkorniie.controller.dao.BillDAO;
import ua.mkorniie.controller.dao.RoomDAO;
import ua.mkorniie.controller.util.Localization;
import ua.mkorniie.model.enums.RoomClass;
import ua.mkorniie.model.pojo.Room;

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

        if ("/admin-update-rooms".equals(action)) {
            try {
                String pictureURL = request.getParameter("picture");
                log("Successfully getting pictureURL: " + pictureURL);

                int places = Integer.parseInt(request.getParameter("places"));
                log("Successfully getting number of places: " + places);

                RoomClass roomClass = RoomClass.valueOf(request.getParameter("roomClass"));
                log("Successfully getting class of rooms: " + roomClass);

                double price = Double.parseDouble(request.getParameter("price"));
                log("Successfully getting price of rooms (double value): " + price);

                log("Successfully setting isOccupied (boolean value) to false (by default). ");
                roomDAO.insert(new Room(places, roomClass, false, pictureURL, price));
            } catch (Exception e) {
                log("Unable to create Room object.");
            }

            showPage(request, response);
        } else {
            showNewForm(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Localization.changeLocale(request);
        showNewForm(request, response);
    }

    private void showPage(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws IOException {
        request.setAttribute("bills", new BillDAO().selectAll());
        request.setAttribute("rooms", new RoomDAO().selectAll());
        showNewForm(request, response);
    }

    private void showNewForm(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/admin-tables");
    }
}
