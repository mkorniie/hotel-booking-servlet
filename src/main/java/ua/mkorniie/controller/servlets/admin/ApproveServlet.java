package ua.mkorniie.controller.servlets.admin;

import com.sun.istack.internal.NotNull;
import ua.mkorniie.controller.dao.BillDAO;
import ua.mkorniie.controller.dao.RequestDAO;
import ua.mkorniie.controller.dao.RoomDAO;
import ua.mkorniie.model.pojo.Bill;
import ua.mkorniie.model.pojo.Request;
import ua.mkorniie.model.pojo.Room;
import ua.mkorniie.controller.util.Pagination;
import ua.mkorniie.controller.util.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/approve")
public class ApproveServlet extends HttpServlet {
    private static RequestDAO requestDAO = new RequestDAO();
    private static RoomDAO roomDAO = new RoomDAO();
    private static BillDAO billDAO = new BillDAO();

    private void showApprovePage(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = (Integer) request.getAttribute("req-id");
        Request selected = requestDAO.selectById(id);

        request.setAttribute("selected-request", selected);

        List<Room> matchingRooms = findMatchingRooms(selected);

        Pagination<Room> roomPagination = new Pagination();
        roomPagination.paginate(matchingRooms, request, response);
        request.getRequestDispatcher(Paths.APPROVE_ROOM_REQ.getUrl()).forward(request, response);
    }

    private List<Room> findMatchingRooms(@NotNull Request selected) {
            List<Room> matching = new ArrayList<>();

            List<Room> allRooms = roomDAO.selectAll();

            for (Room r : allRooms) {
                if (r.getRoomClass() == selected.getRoomClass()) {
                    if (r.getPlaces() >= selected.getPlaces()) {
                        if (withinDateRange(r, selected)) {
                            matching.add(r);
                        }
                    }
                }

            }
            return matching;
    }

    private boolean withinDateRange(@NotNull Room r, @NotNull Request selected) {
        List<Bill> bills = billDAO.selectAll();

        for (Bill b : bills) {
            if (b.getRoom().getId() == r.getId()) {
                if (datesOverlap(b, selected)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean datesOverlap(@NotNull Bill b, @NotNull Request selected) {
        Date selectedStart = selected.getDatePair().getStartDate();
        Date selectedEnd = selected.getDatePair().getEndDate();

        Date reqStart = b.getRequest().getDatePair().getStartDate();
        Date reqEnd = b.getRequest().getDatePair().getEndDate();

        if (selectedStart.after(reqEnd) || reqStart.after(selectedEnd)) {
            return false;
        }
        return true;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        String id = request.getParameter("id");

        if(method != null && id != null && method.equals("approve")) {
            try {
                int num = Integer.parseInt(id);
                request.setAttribute("req-id", num);
                showApprovePage(request, response);
            } catch (NumberFormatException e) {
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/admin");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer roomId = Integer.parseInt(request.getParameter("room-select"));
            Integer requestId = Integer.parseInt(request.getParameter("id"));
            request.getParameter("places");

            Room selected = roomDAO.selectById(roomId);
            Request relatedRequest = requestDAO.selectById(requestId);

            relatedRequest.setApproved(true);
            requestDAO.update(relatedRequest);

            Bill bill = new Bill(selected.getPrice(), false, relatedRequest, selected);
            billDAO.insert(bill);
        } catch (Exception e) {
            System.out.println("Error making approving request");
        }
        ServletContext context= getServletContext();
        RequestDispatcher rd= context.getRequestDispatcher("/admin");
        rd.forward(request, response);
    }
}
