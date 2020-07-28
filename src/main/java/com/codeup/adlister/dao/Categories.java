package com.codeup.adlister.dao;
import java.util.List;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

public interface Categories {
    List<Category> listAll();
    String getCategoryByAdsId(int id);
    String getCategory(Ad ad);
    void insertAdCat(Ad ad, String category);
    int getIdByCategory(String category);

}
