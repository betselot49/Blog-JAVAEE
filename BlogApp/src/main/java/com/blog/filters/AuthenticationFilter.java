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
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        User user;
        try {

            user = User.getByEmail((String) session.getAttribute("email"));
            System.out.println("===========" + user.Role + "===========");
            request.setAttribute("user", user);
        }
        catch (Exception ignored) {
        }
        System.out.println("=========Authentication Filter==============");
        filter.doFilter(httpRequest, httpResponse);
    }
}
