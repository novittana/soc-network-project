package com.mycompany.soc.network.project.persistence.dao;

import com.mycompany.soc.network.project.persistence.connection.DBTypes;
import com.mycompany.soc.network.project.persistence.connection.DataSource;

import java.util.Objects;
import java.util.function.Supplier;

public class MySqlDaoFactory extends DaoFactory {
    private static MySqlDaoFactory instance = null;
    private DataSource dataSource;

    private MySqlDaoFactory(DBTypes dbTypes) {
        this.dataSource = DataSource.getInstance(dbTypes);
    }

    public static synchronized MySqlDaoFactory getInstance(DBTypes dbTypes) {
        if (Objects.isNull(instance)) {
            instance = new MySqlDaoFactory(dbTypes);
        }
        return instance;
    }

    @Override
    public <T> T doInTransaction(Supplier<T> operation) {
        return dataSource.<T>doInTransaction(operation);
    }

    @Override
    public DataSource getDataSource(){
        return dataSource;
    }
    @Override
    public IDAOUsersType createUsersTypeDao() {
        return DAOUsersType.getInstance(dataSource);
    }

    @Override
    public DAOUser createUserDao() {
        return DAOUser.getInstance(dataSource);
    }

    @Override
    public IDAOFriendship createFriendshipDao() {
        return DAOFriendship.getInstance(dataSource);
    }

    @Override
    public IDAOMessage createMessageDao() {
        return DAOMessage.getInstance(dataSource);
    }

    @Override
    public IDAOPost createPostDao() {
        return DAOPost.getInstance(dataSource);
    }

    @Override
    public IDAOComment createCommentDao() {
        return DAOComment.getInstance(dataSource);
    }


}
