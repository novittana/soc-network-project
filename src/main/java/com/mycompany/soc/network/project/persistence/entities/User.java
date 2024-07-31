package com.mycompany.soc.network.project.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users", schema = "social_network")
public class User {
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 255)
    @Column(name = "user_name")
    private String userName;

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 255)
    @Column(name = "password_hash")
    private String passwordHash;

    @Size(max = 255)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Size(max = 45)
    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Basic(optional = false)
    @NotNull()
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "edited_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editedAt;

    @Column(name = "is_blocked")
    private Short isBlocked;

    @OneToMany(mappedBy = "commentatorId")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "receiverId")
    private Collection<Message> messageCollection;
    @OneToMany(mappedBy = "senderId")
    private Collection<Message> messageCollection1;
    @OneToMany(mappedBy = "userId1")
    private Collection<Friendship> friendshipCollection;
    @OneToMany(mappedBy = "userId2")
    private Collection<Friendship> friendshipCollection1;
    @OneToMany(mappedBy = "userId")
    private Collection<Post> postCollection;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Lob
    @Column(name = "bio")
    private String bio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type")
    private UsersType userType;
    public User(String userName, String email, String passwordHash, UsersType userType){
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.userType = userType;
    }

    public User(Integer id, String userName, String email, String passwordHash, UsersType userType){
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.userType = userType;
    }


//    public User(Integer id, String userName, String email, String passwordHash, String firstName) {
//        this.id = id;
//        this.userName = userName;
//        this.email = email;
//        this.passwordHash = passwordHash;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
//        this.bio = bio;
//        this.profilePictureUrl = profilePictureUrl;
//        this.createdAt = createdAt;
//        this.editedAt = editedAt;
//        this.userType = userType;
//    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public LocalDate getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(LocalDate dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

//    public Instant getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Instant createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Instant getEditedAt() {
//        return editedAt;
//    }
//
//    public void setEditedAt(Instant editedAt) {
//        this.editedAt = editedAt;
//    }

    public UsersType getUserType() {
        return userType;
    }

    public void setUserType(UsersType userType) {
        this.userType = userType;
    }

    public Short getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Short isBlocked) {
        this.isBlocked = isBlocked;
    }

    public User(int anInt, String string, String resultSetString, String setString, User user) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    public Collection<Message> getMessageCollection1() {
        return messageCollection1;
    }

    public void setMessageCollection1(Collection<Message> messageCollection1) {
        this.messageCollection1 = messageCollection1;
    }

    public Collection<Friendship> getFriendshipCollection() {
        return friendshipCollection;
    }

    public void setFriendshipCollection(Collection<Friendship> friendshipCollection) {
        this.friendshipCollection = friendshipCollection;
    }

    public Collection<Friendship> getFriendshipCollection1() {
        return friendshipCollection1;
    }

    public void setFriendshipCollection1(Collection<Friendship> friendshipCollection1) {
        this.friendshipCollection1 = friendshipCollection1;
    }

    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{")
                .append("id=").append(id)
                .append(", userName='").append(userName).append('\'')
                .append(", email='").append(email).append('\'')
                .append(", passwordHash=").append(passwordHash)
//                .append(", firstName=").append(firstName)
//                .append(", lastName=").append(lastName)
//                .append(", dateOfBirth=").append(dateOfBirth)
//                .append(", bio='").append(bio).append('\'')
//                .append(", profilePictureUrl='").append(profilePictureUrl).append('\'')
//                .append(", createdAt=").append(createdAt)
//                .append(", editedAt=").append(editedAt)
                .append(", userType=").append(userType)
                .append('}');
        return sb.toString();
    }



}