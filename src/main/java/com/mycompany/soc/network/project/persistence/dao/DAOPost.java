package com.mycompany.soc.network.project.persistence.dao;
import com.mycompany.soc.network.project.persistence.connection.DBTypes;
import com.mycompany.soc.network.project.persistence.connection.DataSource;
import com.mycompany.soc.network.project.persistence.entities.Post;
import com.mycompany.soc.network.project.persistence.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DAOPost implements IDAOPost {

    private static final Logger LOGGER = Logger.getLogger(DAOPost.class.getSimpleName());
    private static DAOPost instance = null;

    private static final String ID_LABEL = "id";
    private static final String USER_ID_LABEL = "user_id";
    private static final String CONTENT_LABEL = "content";
    private static final String IMAGE_URL_LABEL = "image_url";
    private static final String CREATE_AT_LABEL = "created_at";


    private static final String INSERT_POST = "INSERT INTO posts (user_id, content, image_url) VALUES (?, ?, ?)";
    private static final String DELETE_POST = "DELETE FROM posts WHERE id = ?";
    private static final String SELECT_ALL_POST = "SELECT * FROM posts";
    private static final String SELECT_POST_BY_ID = "SELECT * FROM posts WHERE id = ?";
    private static final String UPDATE_POST = "UPDATE posts SET content = ?, image_url = ?, WHERE id = ?";

    private DataSource dataSource;

    private DAOPost(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized DAOPost getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new DAOPost(dataSource);
        }
        return instance;
    }

    @Override
    public Optional<Post> get(long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POST_BY_ID)){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return Optional.of(mapResultSetToPost(resultSet));
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
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_POST)) {
            while (resultSet.next()) {
                posts.add(mapResultSetToPost(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return posts;
    }

    @Override
    public void create(Post post) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_POST);
            ps.setLong(1, post.getUserId());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getImageUrl());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Post post, String[] params) {

    }

    @Override
    public void delete(Post post) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POST)){
            preparedStatement.setLong(1, post.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Post mapResultSetToPost(ResultSet resultSet) throws SQLException {
        return new Post(
                resultSet.getLong(ID_LABEL),
                resultSet.getLong(USER_ID_LABEL),
                resultSet.getString(CONTENT_LABEL),
                resultSet.getString(IMAGE_URL_LABEL),
                resultSet.getDate(CREATE_AT_LABEL));
    }
}
