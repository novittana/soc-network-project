package com.mycompany.soc.network.project.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class DataSource {
    private static final Logger LOGGER = Logger.getLogger(DataSource.class.getName());
    private static DataSource instance = null;
    private String url;
    private String user;
    private String password;
    private Connection connection;

    private DataSource(DBTypes dbTypes) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(dbTypes.getPropertiesFile());
        url = resourceBundle.getString("url");
        user = resourceBundle.getString("user");
        password = resourceBundle.getString("password");
    }

    public static synchronized DataSource getInstance(DBTypes dbTypes) {
        if (instance == null) {
            instance = new DataSource(dbTypes);
        }
        return instance;
    }
    public Connection getConnection() {
        try {
            if (Objects.isNull(connection) || connection.isClosed()) {
                connection = DriverManager.getConnection(url, user, password);
            }

        } catch (SQLException e){
            LOGGER.severe("Error during connection creation: " + e.getMessage());
            throw new RuntimeException("Error during connection creation. Please, check credentials ",e);
        }
        return connection;
    }

    public String getUrl() {
        return url;
    }
    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }

    public <T> T doInTransaction(Supplier<T> operation) {
        T result;
        try {
            connection.setAutoCommit(false);
            try {
                result = operation.get();
                connection.commit();
            } catch (Exception ex) {
                connection.rollback();
                throw new RuntimeException("Transaction failed", ex);
            } finally {
                if (connection !=null) {
                    connection.close();
                }
            }
        } catch (SQLException  e) {
            LOGGER.severe("Error during doInTransaction " + e.getMessage());
            throw new RuntimeException("Error during doInTransaction.", e);
        }
        return result;
    }
}
