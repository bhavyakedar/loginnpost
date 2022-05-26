package com.bhavyakedar.loginpost.controllers;

import com.bhavyakedar.loginpost.LoginpostApplication;
import com.bhavyakedar.loginpost.components.Comment;
import com.bhavyakedar.loginpost.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentsViewController {

    private final CommentService commentService;

    @GetMapping("/comments")
    public ModelAndView loadComments()
    {
        ModelAndView modelAndView = new ModelAndView();
        if(LoginpostApplication.currentUser == null)
            return new ModelAndView("redirect:/loginPage");
        modelAndView.setViewName("comments.html");
        return modelAndView;
    }
}
