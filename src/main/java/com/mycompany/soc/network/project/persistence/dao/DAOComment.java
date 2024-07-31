package com.mycompany.soc.network.project.persistence.dao;

import com.mycompany.soc.network.project.persistence.connection.DataSource;
import com.mycompany.soc.network.project.persistence.entities.Comment;
import com.mycompany.soc.network.project.persistence.entities.Friendship;
import com.mycompany.soc.network.project.persistence.entities.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DAOComment implements IDAOComment{
    private static final Logger LOGGER = Logger.getLogger(DAOComment.class.getSimpleName());
    private static DAOComment instance = null;

    private static final String ID_LABEL = "id";
    private static final String COMMENTATOR_ID_LABEL = "commentator_id";
    private static final String CONTENT_LABEL = "content";
    private static final String CREATED_AT_LABEL = "created_at";
    private static final String POST_ID_LABEL = "post_id";

    private static final String INSERT_COMMENT_SQL = "INSERT INTO comments (commentator_id, content, post_id) VALUES (?, ?)";
    private static final String DELETE_COMMENT = "DELETE FROM comments WHERE id = ?";
    private static final String UPDATE_COMMENT_SQL = "UPDATE comments SET content = ? WHERE id = ?";
    private static final String SELECT_COMMENT_BY_ID_SQL = "SELECT * FROM comments WHERE id = ?";
    private static final String SELECT_ALL_COMMENTS = "SELECT * FROM comments";

    private DataSource dataSource;

    DAOComment(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized DAOComment getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new DAOComment(dataSource);
        }
        return instance;
    }


    @Override
    public Optional<Comment> get(long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENT_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToComment(resultSet));
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
    public List<Comment> getAll() {
        List<Comment> comments = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_COMMENTS)) {
            while (resultSet.next()) {
                comments.add(mapResultSetToComment(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return comments;
    }


    @Override
    public void create(Comment comment) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_COMMENT_SQL);
            ps.setLong(1, comment.getCommentator());
            ps.setString(2, comment.getContent());
            ps.setLong(3, comment.getPostId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Comment comment, String[] params) {

    }

    @Override
    public void delete(Comment comment) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMMENT)){
            preparedStatement.setLong(1, comment.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Comment mapResultSetToComment(ResultSet resultSet) throws SQLException {
        return new Comment (
                resultSet.getLong(ID_LABEL),
                resultSet.getLong(COMMENTATOR_ID_LABEL),
                resultSet.getString(CONTENT_LABEL),
                resultSet.getDate(CREATED_AT_LABEL),
                resultSet.getLong(POST_ID_LABEL));
    }
}
