package com.mycompany.soc.network.project.persistence.dao;

import com.mycompany.soc.network.project.persistence.connection.DBTypes;
import com.mycompany.soc.network.project.persistence.connection.DataSource;
import java.util.function.Supplier;

public abstract class DaoFactory {
    public abstract DataSource getDataSource();

    public abstract IDAOUsersType createUsersTypeDao();

    public abstract IDAOUser createUserDao();

    public abstract IDAOFriendship createFriendshipDao();

    public abstract IDAOMessage createMessageDao();

    public abstract IDAOPost createPostDao();

    public abstract IDAOComment createCommentDao();
    public abstract <T> T doInTransaction(Supplier<T> operation);

    public static DaoFactory getMySqlDAOFactory() {
        return MySqlDaoFactory.getInstance(DBTypes.MYSQL);
    }


    public static DaoFactory createDaoFactory(DBTypes dbTypes) {
        return switch (dbTypes) {
            case MYSQL -> MySqlDaoFactory.getInstance(dbTypes);
        };
    };

}
