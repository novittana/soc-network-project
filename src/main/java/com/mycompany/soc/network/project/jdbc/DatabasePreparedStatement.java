package com.mycompany.soc.network.project.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePreparedStatement {

    private static final String INSERT_USER_TYPE = "INSERT INTO users_type(users_type, role) VALUES(?,?)";

    public static void createUserType(String userType, String role) {
        try (Connection connection = DatabaseConnection.getConnection()){
                PreparedStatement ps =connection.prepareStatement(INSERT_USER_TYPE);
                ps.setString(1, userType);
                ps.setString(2, role);
                ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertIntoTable() {
    }

}
