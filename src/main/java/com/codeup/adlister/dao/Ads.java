package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);
    int getIdFromAd(String title);
    // find Ad by ID
    Ad findById(int id);
    // get a list of searched ads
    List<Ad> findAds(String keywords);
}
