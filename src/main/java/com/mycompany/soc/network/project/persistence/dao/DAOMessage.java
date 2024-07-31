package com.mycompany.soc.network.project.persistence.dao;


import com.mycompany.soc.network.project.persistence.connection.DataSource;
import com.mycompany.soc.network.project.persistence.entities.Message;
import com.mycompany.soc.network.project.persistence.entities.Post;
import com.mycompany.soc.network.project.persistence.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DAOMessage implements IDAOMessage{

    private static final Logger LOGGER = Logger.getLogger(DAOMessage.class.getSimpleName());
    private static DAOMessage instance = null;

    private static final String ID_LABEL = "id";
    private static final String SENDER_ID_LABEL = "sender_id";
    private static final String RECEIVER_ID_LABEL = "receiver_id";
    private static final String MESSAGE_TEXT_LABEL = "message_text";
    private static final String CREATED_AT_LABEL = "created_at";
    private static final String EDITED_AT_LABEL = "edited_at";

    private static final String INSERT_MESSAGE_SQL = "INSERT INTO messages (sender_id, receiver_id, message_text) VALUES (?,?,?)";
    private static final String DELETE_MESSAGE = "DELETE FROM messages WHERE id = ?";
    private static final String SELECT_MESSAGE_BY_ID_SQL = "SELECT * FROM messages WHERE id = ?";
    private static final String UPDATE_MESSAGE_SQL = "UPDATE messages SET message_text = ? WHERE id= ?";
    private static final String SELECT_ALL_MESSAGES = "SELECT * FROM messages";


    private DataSource dataSource;

    DAOMessage(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized DAOMessage getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new DAOMessage(dataSource);
        }
        return instance;
    }



    @Override
    public Optional<Message> get(long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MESSAGE_BY_ID_SQL)){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return Optional.of((Message) resultSet);
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
    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_MESSAGES)) {
            while (resultSet.next()) {
                messages.add(mapResultSetToMessage(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return messages;
    }

    @Override
    public void create(Message message) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE_SQL);
            ps.setLong(1, message.getSender());
            ps.setLong(2, message.getReceiver());
            ps.setString(3, message.getMessageText());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Message message, String[] params) {

    }

    @Override
    public void delete(Message message) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MESSAGE)){
            preparedStatement.setLong(1, message.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Message mapResultSetToMessage(ResultSet resultSet) throws SQLException {
        return new Message (
                resultSet.getLong(ID_LABEL),
                resultSet.getLong(SENDER_ID_LABEL),
                resultSet.getLong(RECEIVER_ID_LABEL),
                resultSet.getString(MESSAGE_TEXT_LABEL),
                resultSet.getDate(CREATED_AT_LABEL),
                resultSet.getDate(EDITED_AT_LABEL));
    }
}
