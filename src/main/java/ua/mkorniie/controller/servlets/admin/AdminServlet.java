package ua.mkorniie.controller.servlets.admin;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import ua.mkorniie.controller.dao.*;
import ua.mkorniie.controller.util.Pagination;
import ua.mkorniie.controller.util.PasswordEncoder;
import ua.mkorniie.model.enums.Language;
import ua.mkorniie.model.enums.Role;
import ua.mkorniie.model.pojo.Bill;
import ua.mkorniie.model.pojo.Request;
import ua.mkorniie.model.pojo.Room;
import ua.mkorniie.model.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = {"/admin", "/admin-users", "/admin-tables", "/admin-update", "/admin-users-update"})
public class AdminServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AdminServlet.class);

    private Pagination<Request> requestPagination = new Pagination<>();
    private Pagination<User>    userPagination = new Pagination<>();
    private Pagination<Room>    roomPagination = new Pagination<>();
    private Pagination<Bill>    billPagination = new Pagination<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/admin":
                doGet(request, response);
                break;
            case "/admin-update":
                showAdminPage(request, response);
                break;
            case "/admin-users-update":
                String name = request.getParameter("name");
                String password = PasswordEncoder.getSHA(request.getParameter("pass"));
                Role role = Role.valueOf(request.getParameter("role"));
                String email = request.getParameter("mail");
                Language lang = Language.valueOf(request.getParameter("lang"));
                new UserDAO().insert(new User(name, role, password, email, lang));
                showUserTable(request, response);
                break;
            default:
                showNewForm(request, response, "success_admin.jsp");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/admin":
                processAdminRequest(request, response);
                break;
            case "/admin-users":
                processUserManagementPageRequest(request, response);
                break;
            case "/admin-tables":
                billPagination.paginate("-bills", new BillDAO().selectAll(), request, response);
                roomPagination.paginate("-rooms", new RoomDAO().selectAll(), request, response);
                showNewForm(request, response, "tables.jsp");
                break;
            case "/admin-stats":
                showNewForm(request, response, "statistics.jsp");
                break;
            default:
                showNewForm(request, response, "success_admin.jsp");
                break;
        }
    }

    private void showNewForm(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull String filename)
            throws ServletException, IOException {
        String templatePath = "templates/admin/";
        request.getRequestDispatcher(templatePath + filename).forward(request, response);
    }

    private void showAdminPage(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {

        List<Request> requests = new RequestDAO().selectAll().stream()
                                        .filter(r -> !r.isApproved())
                                        .collect(Collectors.toList());

        requestPagination.paginate(requests, request, response);
        showNewForm(request, response, "success_admin.jsp");
    }

    private void forwardToServlet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
//        ServletContext servletContext = getServletContext();
//        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/approve");
//        requestDispatcher.forward(request, response);
        request.getRequestDispatcher("/approve").forward(request, response);
    }

    private void processAdminRequest(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException{
        String method = request.getParameter("method");
        String id = request.getParameter("id");
        boolean invalid = false;

        if(method != null && id != null && method.equals("approve")) {
            try {
                int num = Integer.parseInt(id);
                request.setAttribute("req-id", num);
                forwardToServlet(request, response);
            } catch (NumberFormatException e) {
                invalid = true;
                logger.error(e.getMessage());
            }
            if (!invalid) {
                return;
            }

        }
        showAdminPage(request, response);
    }

    private void processUserManagementPageRequest(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException{
        String method = request.getParameter("method");
        String id = request.getParameter("id");
        UserDAOProxy proxyDao = new UserDAOProxy();

        if(method != null && id != null) {
            switch (method) {
                case "remove":
                    proxyDao.delete(id);
                    break;
                case "priviledge_a":
                    proxyDao.toAdmin(id);
                    break;
                case "priviledge_u":
                    proxyDao.toUser(id);
                    break;
            }
        }
        showUserTable(request, response);
    }

    private void showUserTable(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        List<User> userList = new UserDAO().selectAll();
        userPagination.paginate(userList, request, response);

        showNewForm(request, response, "users_management.jsp");
    }
}
