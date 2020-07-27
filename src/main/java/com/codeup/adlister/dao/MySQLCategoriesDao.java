package com.codeup.adlister.dao;
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

    private Category extractCategory(ResultSet rs) throws SQLException {
        return new Category(
                rs.getInt("id"),
                rs.getString("name")
        );
    }
}
