package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.setAttribute("categories", DaoFactory.getCategoriesDao().listAll());
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean invalidAdInput =
                title.isEmpty()
                        || description.isEmpty();

        if (invalidAdInput) {
            request.setAttribute("inputIsNull", true);
            request.setAttribute("categories", DaoFactory.getCategoriesDao().listAll());
            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
        } else {
            Ad ad = new Ad(
                    user.getId(),
                    title,
                    description
            );
            Long num = DaoFactory.getAdsDao().insert(ad);
            if (num > 0) {
                DaoFactory.getCategoriesDao().insertAdCat(ad, request.getParameter("category"));
            }
            response.sendRedirect("/ads");
        }
    }
}
