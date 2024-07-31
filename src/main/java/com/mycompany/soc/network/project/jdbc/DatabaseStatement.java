//package com.mycompany.soc.network.project.jdbc;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//
//import java.sql.*;
//
//public class DatabaseStatement {
//
//    public static void selectUserType(){
//        try {
//            Connection connection = DatabaseConnection.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM users_type");
//            while (resultSet.next()) System.out.println(resultSet.getString(2));
//            connection.close();
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//
//
//}
