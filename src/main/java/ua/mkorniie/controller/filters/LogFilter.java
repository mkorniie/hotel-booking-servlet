package ua.mkorniie.controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * TODO: log not working in filters?
 */

@WebFilter("/*")
public class LogFilter implements Filter {
    private static final Logger logger = Logger.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("Log filter initialized");
        System.out.println("Log filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String servletPath = req.getServletPath();

        logger.info(" ServletPath :" + servletPath + ", URL =" + req.getRequestURL());
        System.out.println(" ServletPath :" + servletPath + ", URL =" + req.getRequestURL());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("destroyed");
        System.out.println("Log filter destroyed");
    }
}
