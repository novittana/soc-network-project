package com.mycompany.soc.network.project.persistence.dao;

import com.mycompany.soc.network.project.persistence.connection.DataSource;
import com.mycompany.soc.network.project.persistence.entities.Friendship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DAOFriendship implements IDAOFriendship {


    private static final Logger LOGGER = Logger.getLogger(DAOFriendship.class.getSimpleName());
    private static DAOFriendship instance = null;

    private static final String ID_LABEL = "id";

    private static final String INSERT_FRIENDSHIP_SQL = "INSERT INTO friendship (userId1, userId2) VALUES (?,?,?)";
    private static final String UPDATE_FRIENDSHIP_SQL = "UPDATE friendship SET user_id=? WHERE id=?";
    private static final String DELETE_FRIENDSHIP = "DELETE FROM friendship WHERE id = ?";
    private static final String SELECT_ALL_FRIENDSHIP = "SELECT * FROM friendship";
    private static final String SELECT_FRIENDSHIP_BY_ID_SQL = "SELECT * FROM friendship WHERE id=?";


    private DataSource dataSource;

    DAOFriendship() {
        this.dataSource = dataSource;
    }

    public static synchronized DAOFriendship getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new DAOFriendship();
        }
        return instance;
    }


    @Override
    public Optional<Friendship> get(long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FRIENDSHIP)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of((Friendship) resultSet);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Friendship> getAll() {
        return List.of();
    }

    @Override
    public void create(Friendship friendship) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_FRIENDSHIP_SQL);
            ps.setString(1, String.valueOf(friendship.getUserId1()));
            ps.setString(2, String.valueOf(friendship.getUserId2()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Friendship friendship, String[] params) {

    }

    @Override
    public void update(Friendship friendship) {

    }

    @Override
    public void delete(Friendship friendship) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FRIENDSHIP)) {
            preparedStatement.setLong(1, friendship.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
