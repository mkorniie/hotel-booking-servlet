package ua.mkorniie.controller.filters;

import com.sun.istack.internal.NotNull;
import ua.mkorniie.model.enums.Role;
import ua.mkorniie.model.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static ua.mkorniie.controller.util.Paths.ACCESS_ERROR_PAGE;
import static ua.mkorniie.controller.util.Paths.GENERAL_ERROR;
import static ua.mkorniie.controller.util.SessionTool.getUser;

// Сам по себе - шаблон "Цепочка ответственности"?
@WebFilter("/*")
public class AccessFilter implements Filter {
    private static final List<String> adminPages = Arrays.asList("/admin", "/admin-users", "/admin-tables",
            "/admin-update", "/admin-users-update", "/approve", "/admin-update-rooms", "/logout");

    private static final List<String> userPages = Arrays.asList( "/user-main",
            "/user-my-bills", "/new-request", "/user-my-requests", "/logout");

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("Access filter initialized");
    }

    private static boolean requiresAdminRights(@NotNull String servletPath) {
        return adminPages.stream().filter(servletPath::equals).count() == 1;
    }

    private static boolean  requiresUserRights(@NotNull String servletPath) {
        return userPages.stream().filter(servletPath::equals).count() == 1;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = req.getServletPath();
        User user = getUser(req.getSession());
        if (!requiresUserRights(servletPath) && !requiresAdminRights(servletPath)) {
            if (user == null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (user.getRole() == Role.USER) {
                response.sendRedirect("/user-main");
            } else if (user.getRole() == Role.ADMIN) {
                response.sendRedirect("/admin");
            } else {
                response.sendRedirect(GENERAL_ERROR.getUrl());
            }
            return;
        } else if (user != null && isAuthorized(servletPath, user)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
        }
        System.out.println("No rights: redirecting back");
        response.sendRedirect(ACCESS_ERROR_PAGE.getUrl());
    }

    // TODO: check operation priority
    private boolean isAuthorized(@NotNull String servletPath, @NotNull User user) {
        return ((requiresAdminRights(servletPath)) && (user.getRole() == Role.ADMIN)) ||
                    ((requiresUserRights(servletPath)) && (user.getRole() == Role.USER));
    }

    @Override
    public void destroy() {
        System.out.println("Access filter destroyed");
    }
}
