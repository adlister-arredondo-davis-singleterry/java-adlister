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

import static java.lang.Long.parseLong;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ads", DaoFactory.getAdsDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String newTitle = req.getParameter("title");
        String newDescription = req.getParameter("description");
        String newCategory = req.getParameter("category");
        boolean invalidAdInput =
                newTitle.isEmpty()
                        || newDescription.isEmpty();

        if (invalidAdInput) {
            req.setAttribute("inputIsNull", true);
            req.getRequestDispatcher("/WEB-INF/ads/edit-ad.jsp").forward(req, resp);

        } else {

            String passedId = req.getParameter("editId");
            Long newAdId = parseLong(passedId);

            Ad newAd = new Ad(newAdId, user.getId(), newTitle, newDescription);
            newAd.setCategory(newCategory);
            System.out.println(newAd.getCategory());
            DaoFactory.getAdsDao().updateAd(newAd);
            resp.sendRedirect("/ads");
        }
    }
}
