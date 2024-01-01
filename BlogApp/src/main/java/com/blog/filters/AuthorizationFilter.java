//package com.blog.filters;
//
//import java.io.IOException;
//
//
//import com.blog.config.URLConfig;
//import com.blog.models.User;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@WebFilter("/admin/*")
//public class AuthorizationFilter implements Filter {
////    public void init(FilterConfig config) throws ServletException {
////    }
////    public void destroy() {
////    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
//            throws IOException, ServletException {
//        System.out.println("=========Admin Authorization Filter==============");
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session =httpRequest.getSession();
//        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
//        User user = (User) httpRequest.getAttribute("user");
//        if (user == null || !user.Role.equals("admin")) {
//            httpResponse.sendRedirect(URLConfig.rootUrl + "/login");
//        }else{
//            filter.doFilter(httpRequest, httpResponse);
//        }
//    }
//}