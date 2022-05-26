package com.bhavyakedar.loginpost.components;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

public class Comment {
    @Id
    private String commentId;
    @Indexed
    private String username;
    private String comment;
    private LocalDateTime createdAt;

    public Comment(String username, String comment, LocalDateTime createdAt) {
        this.username = username;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
