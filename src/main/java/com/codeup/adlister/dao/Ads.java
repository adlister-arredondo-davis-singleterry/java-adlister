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
    Ad findById(Long id);
    // get a list of searched ads
    List<Ad> findAds(String keywords);
    // get a list of ads based on ID
    List<Ad> findAds(Long id);
    // delete an ad
    boolean deleteAd(Long id);
    // updaate an ad
    void updateAd(Ad adToBeUpdate);
// ======NEW CODE=============
    List<Ad> setCategoryWithAd(List<Ad> ad);
//    ==========================
}
