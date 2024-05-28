package org.example.websitesellingphonesbackend.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/admin_authentication")) {
            if (request.getSession().getAttribute("admin") == null || (boolean) request.getSession().getAttribute("admin") == false) {
                // Session is null or doesn't have the required attribute
                // Redirect to login page or handle as needed
                response.sendRedirect("/customer_authentication/login");
                return false; // Stop further processing
            }
        }
        return true; // Continue processing the request
    }
}