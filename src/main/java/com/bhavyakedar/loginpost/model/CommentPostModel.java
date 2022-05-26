package com.bhavyakedar.loginpost.model;

public class CommentPostModel {
    String comment;

    public CommentPostModel(){}

    public CommentPostModel(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
