//package com.blog.filters;
//
//import com.blog.models.User;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebFilter(urlPatterns = {"/login/*", "/register/*", "/adminregister/*"})
//public class AuthValidatorFilter implements Filter {
//
//    @Override
//    public void doFilter (ServletRequest request, ServletResponse response, FilterChain filter)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        User user = (User) httpRequest.getAttribute("user");
//        if (user == null || !user.Role.equals("admin")) {
//            httpResponse.sendRedirect("/blog");
//        }else{
//            filter.doFilter(httpRequest, httpResponse);
//        }
//    }
//}
