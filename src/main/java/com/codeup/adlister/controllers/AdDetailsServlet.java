package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static java.lang.Long.parseLong;

@WebServlet(name = "AdDetailsServlet", urlPatterns = "/ad-info/show")
public class AdDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adDetails = req.getParameter("id");
        Long adId = parseLong(adDetails);

        Ad thisAd = DaoFactory.getAdsDao().findById(adId);
        thisAd.setCategory(DaoFactory.getCategoriesDao().getCategory(thisAd));
        req.setAttribute("selectedAd", thisAd);
        System.out.println(req.getAttribute("selectedAd"));
        req.getRequestDispatcher("/WEB-INF/ads/ad-info.jsp").forward(req,resp);
    }
}
