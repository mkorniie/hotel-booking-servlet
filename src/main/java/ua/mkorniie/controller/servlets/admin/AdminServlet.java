package ua.mkorniie.controller.servlets.admin;

import com.sun.istack.internal.NotNull;
import ua.mkorniie.controller.dao.*;
import ua.mkorniie.model.util.Pagination;
import ua.mkorniie.model.enums.Language;
import ua.mkorniie.model.enums.Role;
import ua.mkorniie.model.pojo.Bill;
import ua.mkorniie.model.pojo.Request;
import ua.mkorniie.model.pojo.Room;
import ua.mkorniie.model.pojo.User;
import ua.mkorniie.model.util.PasswordEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
                String name = request.getParameter("name");
//                String country =request.getParameter("country");
//                String description = request.getParameter("desc");
//                new RoomDAO().insert(new Publisher(name, country, description));
                showAdminPage(request, response);
                break;
            case "/admin-users-update":
                name = request.getParameter("name");
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

    //TODO: add filters everywhere (user req, etc.)
    //TODO: switch to JSTL
    private void showAdminPage(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {

        List<Request> requests = new RequestDAO().selectAll().stream()
                                        .filter(r -> !r.isApproved())
                                        .collect(Collectors.toList());

        requestPagination.paginate(requests, request, response);
        showNewForm(request, response, "success_admin.jsp");
    }

    private void forwardToServlet(@NotNull String path, @NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    private void processAdminRequest(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException{
        String method = request.getParameter("method");
        String id = request.getParameter("id");

        if(method != null && id != null && method.equals("approve")) {
            try {
                int num = Integer.parseInt(id);
                request.setAttribute("req-id", num);
                forwardToServlet("/approve", request, response);
            } catch (NumberFormatException e) {
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
            if (method.equals("remove"))
                proxyDao.delete(id);
            else if (method.equals("priviledge_a")){
                proxyDao.toAdmin(id);
            }
            else if (method.equals("priviledge_u")){
                proxyDao.toUser(id);
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
