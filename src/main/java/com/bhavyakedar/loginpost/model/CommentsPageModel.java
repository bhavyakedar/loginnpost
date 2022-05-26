package com.bhavyakedar.loginpost.model;

import com.bhavyakedar.loginpost.components.Comment;
import com.bhavyakedar.loginpost.components.User;

import java.util.List;

public class CommentsPageModel {
    User currentUser;
    List<Comment> comments;

    public CommentsPageModel(){}
    public CommentsPageModel(User currentUser, List<Comment> comments) {
        this.currentUser = currentUser;
        this.comments = comments;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
