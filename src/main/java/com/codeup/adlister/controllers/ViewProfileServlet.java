package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

import static java.lang.Long.parseLong;

@WebServlet(name = "controllers.ViewProfileServlet", urlPatterns = "/profile")
public class ViewProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String deleteUser = request.getParameter("deleteButton");
        Long myId = parseLong(deleteUser);

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        if (deleteUser != null) {
        String userInput = JOptionPane.showInputDialog(null,
                "Are you sure you want to delete your account?\n" +
                         "All Ads will be removed!\n\n" +
                         "Type 'delete' to confirm:",
                "Delete Account Confirmation", JOptionPane.QUESTION_MESSAGE);

            // Get user confirmation before deleting account
            if (userInput == null) {
                // User clicked Cancel
                response.sendRedirect("/profile");
            } else if (userInput.equalsIgnoreCase("delete")) {
                // User typed "delete"
                DaoFactory.getUsersDao().delete(myId);
                request.getSession().removeAttribute("user");
                request.getSession().invalidate();
                JOptionPane.showMessageDialog(null,
                        "Account Deleted!",
                        "Delete Account Confirmation", JOptionPane.INFORMATION_MESSAGE);
                response.sendRedirect("/ads");
            } else {
                // User clicked OK but didn't type "delete"
                JOptionPane.showMessageDialog(null,
                        "Nothing was changed!\n" +
                                 "The word 'delete' was not entered.",
                        "Delete Account Failed", JOptionPane.WARNING_MESSAGE);
                response.sendRedirect("/profile");
            }

        } else {
            request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
        }

    }
}
