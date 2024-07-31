package com.mycompany.soc.network.project.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "messages", schema = "social_network")
public class Message {

    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "edited_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editedAt;
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Long sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Long receiver;

    @Lob
    @Column(name = "message_text")

    private String messageText;

    public Message(Long id, Long sender, Long receiver, String messageText, Date createdAt, Date editedAt) {
    }

    public Message(Long sender, Long receiver, String messageText, Date createdAt, Date editedAt) {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Message{")
                .append("id=").append(id)
                .append(", sender='").append(sender).append('\'')
                .append(", receiver='").append(receiver)
                .append(", messageText='").append(messageText)
                .append(", createdAt='").append(createdAt)
                .append(", editedAt='").append(editedAt)
                .append('}');
        return sb.toString();
    }



//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getEditedAt() {
//        return editedAt;
//    }
//
//    public void setEditedAt(Date editedAt) {
//        this.editedAt = editedAt;
//    }

}