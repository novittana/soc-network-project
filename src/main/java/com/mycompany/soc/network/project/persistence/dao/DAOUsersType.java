package com.mycompany.soc.network.project.persistence.dao;

import com.mycompany.soc.network.project.persistence.connection.DataSource;
import com.mycompany.soc.network.project.persistence.entities.UsersType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DAOUsersType implements IDAOUsersType {
    private static final Logger LOGGER = Logger.getLogger(DAOUsersType.class.getSimpleName());
    private static DAOUsersType instance = null;

    private static final String ID_LABEL = "id";
    private static final String TYPE_LABEL = "users_type";
    private static final String ROLE_LABEL = "role";

    private static final String INSERT_USER_TYPE = "INSERT INTO users_type(users_type, role) VALUES(?,?)";
    private static final String SELECT_USER_TYPE = "SELECT * FROM users_type WHERE id = ?";
    private static final String DELETE_USER_TYPE = "DELETE FROM users_type WHERE id = ?";
    private static final String UPDATE_USER_TYPE = "UPDATE users_type SET  users_type = ?, role = ? WHERE id = ?";
    private static final String SELECT_ALL_USER_TYPE_SQL = "SELECT * FROM users_type";

    private DataSource dataSource;

    private DAOUsersType(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized DAOUsersType getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new DAOUsersType(dataSource);
        }
        return instance;
    }


    @Override
    public Optional<UsersType> get(long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_TYPE)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToUsersType(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<UsersType> getAll() {
        List<UsersType> usersTypes = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_USER_TYPE_SQL)) {
            while (resultSet.next()) {
                usersTypes.add(mapResultSetToUsersType(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return usersTypes;
    }

    @Override
    public void create(UsersType usersType) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_USER_TYPE);
            ps.setString(1, usersType.getUsersType());
            ps.setString(2, usersType.getRole());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void update(UsersType usersType, String[] params) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_TYPE)) {
            ps.setString(1, params[0] != null ? params[0] : usersType.getUsersType());
            ps.setString(2, params[1] != null ? params[1] : usersType.getRole());
            ps.setInt(3, usersType.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(UsersType usersType) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_TYPE)) {
            preparedStatement.setLong(1, usersType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private UsersType mapResultSetToUsersType(ResultSet resultSet) throws SQLException {
        return new UsersType(
                resultSet.getInt(ID_LABEL),
                resultSet.getString(TYPE_LABEL),
                resultSet.getString(ROLE_LABEL));
    }
}
