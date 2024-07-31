package com.mycompany.soc.network.project.persistence.dao;

import com.mycompany.soc.network.project.persistence.connection.DBTypes;
import com.mycompany.soc.network.project.persistence.connection.DataSource;
import com.mycompany.soc.network.project.persistence.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DAOUser implements IDAOUser {
    private static final Logger LOGGER = Logger.getLogger(DAOUser.class.getSimpleName());
    private static DAOUser instance = null;

    private static final String ID_LABEL = "id";
    private static final String NAME_LABEL = "user_name";
    private static final String EMAIL_LABEL = "email";
    private static final String PASSWORD_LABEL = "password_hash";
    private static final String FIRST_NAME_LABEL = "first_name";
    private static final String LAST_NAME_LABEL = "last_name";
    private static final String DATE_OF_BIRTH_LABEL = "date_of_birth";
    private static final String BIO_LABEL = "bio";
    private static final String USER_TYPE_LABEL = "user_type";


    private static final String INSERT_USER_SQL = "INSERT INTO users (user_name, email, password_hash,  user_type) VALUES (?, ?, ?, ? )";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_NAME = "SELECT * FROM users WHERE user_name = ?";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?";
//    private static final String UPDATE_USER = "UPDATE users SET user_name = ? WHERE NAME = ?";
    private static final String SELECT_ALL_USER_SQL = "SELECT * FROM users";
    private static final String SELECT_USER_BY_NAME_SQL = "SELECT * FROM USER WHERE NAME = ?";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM USER WHERE ID = ?";
    private static final String SELECT_ALL_USER_BY_NAME_SQL = "SELECT * FROM users WHERE user_name = ?";


    private DataSource dataSource;

    private DAOUser(DataSource dataSource){
        this.dataSource = dataSource;
    };


    public static synchronized DAOUser getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new DAOUser(dataSource);
        }
        return instance;
    }

    @Override
    public Optional<User> get(long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToUser(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> usersTypes = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_USER_SQL)) {
            while (resultSet.next()) {
                System.out.println(resultSet);
                usersTypes.add(mapResultSetToUser(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return usersTypes;
    }


@Override
public void create(User user) {
    try (Connection connection = dataSource.getConnection()) {
        PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL);
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPasswordHash());
        ps.setInt(4, user.getUserType().getId());
        ps.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

}

@Override
public void update(User user, String[] params) {

}

@Override
public void delete(User user) {
    Connection connection = dataSource.getConnection();
    try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
        preparedStatement.setLong(1, user.getId());
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
}

private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
    return new User(
            resultSet.getInt(ID_LABEL),
            resultSet.getString(NAME_LABEL),
            resultSet.getString(EMAIL_LABEL),
            resultSet.getString(PASSWORD_LABEL),
            DAOUser.getInstance(DataSource.getInstance(DBTypes.MYSQL)).get(resultSet.getInt(USER_TYPE_LABEL)).orElseThrow());

}
}
