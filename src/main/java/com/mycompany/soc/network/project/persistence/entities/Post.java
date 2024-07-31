package com.mycompany.soc.network.project.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "posts", schema = "social_network")
public class Post {

    @Size(max = 45)
    @Column(name = "image_url")
    private String imageUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(mappedBy = "postId")
    private Collection<Comment> commentCollection;
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Long userId;

    @Lob
    @Column(name = "content")
    private String content;
    public Post (Long id, Long userId, String content, String imageUrl, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;

    }
    public Post (Long userId, String content, String imageUrl) {
        this.userId = userId;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Post{")
                .append("id=").append(id)
                .append(", user='").append(userId).append('\'')
                .append(", content='").append(content)
                .append(", imageUrl='").append(imageUrl)
                .append(", createdAt='").append(createdAt)
                .append('}');
        return sb.toString();
    }

//@Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Post post = (Post) o;
//        return Objects.equals()
//}
}