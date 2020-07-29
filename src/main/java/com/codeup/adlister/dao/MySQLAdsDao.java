package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return setCategoryWithAd(createAdsFromResults(rs));//===NEW CODE===
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }
    @Override
    public Ad findById(int id){
        return all().get(id -1);
    }

    @Override
    public List<Ad> findAds(String keywords) {
        String query = "SELECT * FROM ads WHERE title LIKE ? OR description LIKE ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + keywords + "%");
            stmt.setString(2, "%" + keywords + "%");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding an ad by keywords", e);
        }
    }

    @Override
    public int getIdFromAd(String title) {
        PreparedStatement statement = null;
        String query = "SELECT id FROM ads WHERE title = '" + title + "'";
        try {
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad_cat.", e);
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
//===================NEW CODE=============================
    @Override
    public List<Ad> setCategoryWithAd(List<Ad> ad) {
        List<Ad> ads;
        PreparedStatement statement = null;
        for (Ad x : ad) {
            String query = "SELECT name FROM categories JOIN ad_category ON categories.id = ad_category.category_id JOIN ads ON ad_category.ad_id = ads.id WHERE ads.id = '" + x.getId() + "'";
            try {
                statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                rs.next();
                x.setCategory(rs.getString("name"));
            } catch (SQLException e) {
                throw new RuntimeException("Error adding category to ad.", e);
            }
        }
        return ad;
    }
//    =======================================================
}
