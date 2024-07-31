package com.mycompany.soc.network.project.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "users_type", schema = "social_network")
public class UsersType {

    @Size(max = 45)
    @Column(name = "users_type")
    private String usersType;
    @Size(max = 45)
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "userType")
    private Collection<User> userCollection;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;


    public UsersType(Integer id, String usersType, String role) {
        this.id = id;
        this.usersType = usersType;
        this.role = role;
    }

    public UsersType(String usersType, String role) {
        this.id = id;
        this.usersType = usersType;
        this.role = role;
    }

    public UsersType() {
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsersType() {
        return usersType;
    }

    public void setUsersType(String usersType) {
        this.usersType = usersType;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usertype{")
                .append("id=").append(id)
                .append(", usersType='").append(usersType).append('\'')
                .append(", role='").append(role)
                .append('}');
        return sb.toString();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Car car = (Car) o;
//        return id == car.id &&
//                isTurnedOn == car.isTurnedOn &&
//                isHandbrakeReleased == car.isHandbrakeReleased &&
//                Objects.equals(brand, car.brand) &&
//                Objects.equals(model, car.model) &&
//                Objects.equals(wheelsList, car.wheelsList);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, isTurnedOn, isHandbrakeReleased, brand, model, wheelsList);
//    }



}