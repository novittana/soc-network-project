package com.mycompany.soc.network.project.persistence.dao;

import com.mycompany.soc.network.project.persistence.entities.Friendship;

public interface IDAOFriendship extends IDAO<Friendship> {
    void update(Friendship friendship);
}
