package com.bhavyakedar.loginpost.controllers;

import com.bhavyakedar.loginpost.LoginpostApplication;
import com.bhavyakedar.loginpost.components.Comment;
import com.bhavyakedar.loginpost.components.User;
import com.bhavyakedar.loginpost.model.CommentPostModel;
import com.bhavyakedar.loginpost.model.CommentsPageModel;
import com.bhavyakedar.loginpost.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class CommentsDataController {

    private final CommentService commentService;

    @GetMapping("/getPostComments")
    public CommentsPageModel getComments()
    {
        List<Comment> comments = commentService.getAllComments();
        User currentUser = LoginpostApplication.currentUser;
        CommentsPageModel commentsPageModel = new CommentsPageModel(currentUser, comments);
        return commentsPageModel;
    }

    @PostMapping("/getPostComments")
    public Comment postComment(@RequestBody CommentPostModel commentPostModel)
    {
        Comment comment = new Comment(LoginpostApplication.currentUser.getUsername(),
                commentPostModel.getComment(),
                LocalDateTime.now());
        return commentService.postComment(comment);
    }

}
