package com.blog.filters;

import java.io.IOException;


import com.blog.models.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
            throws IOException, ServletException {
        System.out.println("=========Authentication Filter==============");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session =httpRequest.getSession();
	    String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        User user;
        try {
            System.out.println("=========Hereeee==============");
            user = User.getByEmail((String) session.getAttribute("email"));
            request.setAttribute("user", user);
        }
        catch (Exception ignored) {
            System.out.println("=========Failed==============");
            System.out.println(ignored);
        }
        filter.doFilter(httpRequest, httpResponse);
    }
}
