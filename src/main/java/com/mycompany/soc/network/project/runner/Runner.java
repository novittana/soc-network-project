package com.mycompany.soc.network.project.runner;

import com.mycompany.soc.network.project.persistence.dao.DaoFactory;
import com.mycompany.soc.network.project.persistence.dao.IDAOPost;
import com.mycompany.soc.network.project.persistence.dao.IDAOUser;
import com.mycompany.soc.network.project.persistence.dao.IDAOUsersType;
import com.mycompany.soc.network.project.persistence.entities.Post;
import com.mycompany.soc.network.project.persistence.entities.User;
import com.mycompany.soc.network.project.persistence.entities.UsersType;

import java.util.List;
import java.util.Optional;

public class Runner {
    public static void main(String[] args) {
        DaoFactory daoFactory = DaoFactory.getMySqlDAOFactory();
        IDAOUsersType daoUsersType = daoFactory.createUsersTypeDao();
        IDAOUser daoUser = daoFactory.createUserDao();
        IDAOPost daoPost = daoFactory.createPostDao();


//Post newPost = new Post (6L,"Hello World","https://wertyuiopkjhjklkn");
//daoPost.create(newPost);

//List<Post> postsList = daoPost.getAll();
//System.out.println(postsList);
//Optional<Post> optionalPost = daoPost.get(3);
//Post post = optionalPost.get();
//        daoPost.delete(post);
//
//System.out.println("!!!!!" + usType);
//
//
//daoUsersType.update(usType, new String[]{"user","user",});


//        System.out.println(daoUsersType.delete();
//        UsersType usersType1 = new UsersType("master", "master");
//daoUsersType.create(usersType1);

//        List<UsersType> usersTypesList = daoUsersType.getAll();
//        System.out.println(usersTypesList);
//        Optional<UsersType> optionalUsersType = daoUsersType.get(4);
//        System.out.println(optionalUsersType);

//        UsersType usersType = new UsersType(4, "master", "master");
//        usersType.setId(4);
//        usersType.setUsersType("master"); // Поточне або нове значення типу
//        usersType.setRole("master");



//        String[] params = {"customer", "customer", };
//        daoUsersType.update(usersType, params);
//
//        daoUsersType.update(daoUsersType.get(2).orElseThrow(),("user"));
//        System.out.println(usersTypesList);
//
//System.out.println(optionalUsersType);
//        User user = new User(1,"Ivan", "ivan@gmail.com", "root", daoUser.get(3));
//       daoUser.create(user);

//        daoUser.create(new User("Bob", "bob@gmail.com", "root", daoUsersType.get(3).orElseThrow()));

//       Optional<User> optionalUser = daoUser.get(1);
//       System.out.println(optionalUser);
//       List<User> userList = daoUser.getAll();
//       System.out.println(userList);
//       daoUser.delete(daoUser.get(6).orElseThrow());
//        System.out.println(userList);
    }
}
