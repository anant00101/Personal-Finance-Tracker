package com.example.finance.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private static final Map<String, String[]> userDatabase = new HashMap<>();

    static {
        userDatabase.put("john", new String[]{"John Doe", "john@example.com", "1234 Main St"});
        userDatabase.put("jane", new String[]{"Jane Smith", "jane@example.com", "5678 Elm St"});
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        if (username != null) {
            String[] userDetails = userDatabase.get(username);
            request.setAttribute("userDetails", userDetails);
            request.getRequestDispatcher("/WEB-INF/views/userDetails.jsp").forward(request, response);
        } else {
            request.setAttribute("userList", userDatabase.keySet());
            request.getRequestDispatcher("/WEB-INF/views/userList.jsp").forward(request, response);
        }
    }
}
