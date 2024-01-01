package com.blog.filters;

import java.io.IOException;


import com.blog.config.URLConfig;
import com.blog.models.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/login/*", "/register/*"})
public class PublicFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
            throws IOException, ServletException {
        System.out.println("=========Public Filter==============");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session =httpRequest.getSession();
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        User user = (User) httpRequest.getAttribute("user");
        if (user != null ) {
            httpResponse.sendRedirect(URLConfig.rootUrl + "/blog");
        }else{
            filter.doFilter(httpRequest, httpResponse);
        }
    }
}