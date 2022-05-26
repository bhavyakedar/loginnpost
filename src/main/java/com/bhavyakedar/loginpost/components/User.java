package com.bhavyakedar.loginpost.components;

import jdk.jfr.DataAmount;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class User {

    @Id
    private String userId;
    @Indexed(unique = true)
    private String username;
    private String name;
    private String email;
    private LocalDateTime userCreatedAt;

    private String password;

    public User(String username,
                String name,
                String email,
                LocalDateTime userCreatedAt,
                String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.userCreatedAt = userCreatedAt;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getUserCreatedAt() {
        return userCreatedAt;
    }

    public void setUserCreatedAt(LocalDateTime userCreatedAt) {
        this.userCreatedAt = userCreatedAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
