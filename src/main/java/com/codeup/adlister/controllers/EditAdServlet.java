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

@WebServlet(name = "EditAdServlet", urlPatterns = "/edit-ad")
public class EditAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("/login");
        }
        req.getRequestDispatcher("/WEB-INF/ads/edit-ad.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String editAd = req.getParameter("editAd");
        long adId = parseLong(editAd);
        req.setAttribute("newAdId", adId);
        req.setAttribute("categories", DaoFactory.getCategoriesDao().listAll());
        req.setAttribute("adToEdit", DaoFactory.getAdsDao().findById(adId));

        req.getRequestDispatcher("/WEB-INF/ads/edit-ad.jsp").forward(req,resp);

    }
}
