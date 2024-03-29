package com.blog.utils;

import jakarta.servlet.http.HttpServletRequest;

public class Helpers {
        public static String getLastPathSegment(HttpServletRequest request) {
            String pathInfo = request.getPathInfo();
            if (pathInfo != null && pathInfo.length() > 1) {
                String[] pathSegments = pathInfo.split("/");

                return pathSegments[pathSegments.length - 1];
            }
            return null;
        }

        public static  boolean isNotificationRequest(HttpServletRequest request){
            if ( request.getAttribute("success") != null || request.getAttribute("error") != null){
                return true;
            }
            return false;
        }
}
