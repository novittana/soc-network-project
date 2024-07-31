package com.mycompany.soc.network.project.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "friendships", schema = "social_network")
public class Friendship {

    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Size(max = 8)
    @Column(name = "status")
    private String status;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id1")
    private Long userId1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id2")
    private Long userId2;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId1() {
        return userId1;
    }

    public void setUserId1(Long userId1) {
        this.userId1 = userId1;
    }

    public Long getUserId2() {
        return userId2;
    }

    public void setUserId2(Long userId2) {
        this.userId2 = userId2;
    }

//    public Instant getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Instant createdAt) {
//        this.createdAt = createdAt;
//    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usertype{")
                .append("id=").append(id)
                .append(", userId1='").append(userId1).append('\'')
                .append(", userId2='").append(userId2)
//                .append(", createdAt=").append(createdAt)
                .append('}');
        return sb.toString();
    }

    public Friendship() {
    }


}