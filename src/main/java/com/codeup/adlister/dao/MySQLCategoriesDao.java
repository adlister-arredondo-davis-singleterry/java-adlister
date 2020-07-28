package com.codeup.adlister.dao;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements Categories {
    private Connection connection;

    public MySQLCategoriesDao(Config config) {
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
    public List<Category> listAll() {
        List<Category> categories = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                categories.add(extractCategory(rs));
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all categories", e);
        }
    }

    @Override
    public String getCategoryByAdsId(int id) {
        return null;
    }

    @Override
    public String getCategory(Ad ad) {
        String output;
        PreparedStatement statement = null;
        String query = "SELECT name FROM categories JOIN ad_category ON categories.id = ad_category.category_id JOIN ads ON ad_category.ad_id = ads.id WHERE ads.title = '" + ad.getTitle() + "'";
        try {
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            rs.next();
            output = rs.getString("name");
            return output;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving the category", e);
        }
    }

    @Override
    public int getIdByCategory(String category) {
        int output = 0;
        PreparedStatement statement = null;
        String query = "SELECT id FROM categories WHERE name = '" + category + "'";
        try {
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            rs.next();
            output += rs.getInt("id");
            return output;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving the category ID", e);
        }
    }

    @Override
    public void insertAdCat(Ad ad, String category) {
        String insertAdCategoryQuery = "INSERT INTO ad_category(ad_id, category_id) VALUES (?, ?)";
        try {
            PreparedStatement adCatStmt = connection.prepareStatement(insertAdCategoryQuery, Statement.RETURN_GENERATED_KEYS);
            adCatStmt.setLong(1, DaoFactory.getAdsDao().getIdFromAd(ad.getTitle()));
            adCatStmt.setInt(2, DaoFactory.getCategoriesDao().getIdByCategory(category));
            adCatStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad_category.", e);
        }
    }

    private Category extractCategory(ResultSet rs) throws SQLException {
        return new Category(
                rs.getInt("id"),
                rs.getString("name")
        );
    }
}
