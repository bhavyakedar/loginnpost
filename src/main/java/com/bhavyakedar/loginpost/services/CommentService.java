package com.bhavyakedar.loginpost.services;

import com.bhavyakedar.loginpost.components.Comment;
import com.bhavyakedar.loginpost.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    public final MongoTemplate mongoTemplate;
    public final CommentRepository commentRepository;

    public List<Comment> getAllComments()
    {
        return commentRepository.findAll();
    }

    public Comment postComment(Comment comment)
    {
        return commentRepository.save(comment);
    }

    public void deleteUserComments(String username)
    {
        Query queryUsername = new Query();
        queryUsername.addCriteria(Criteria.where("username").is(username));
        mongoTemplate.findAllAndRemove(queryUsername, Comment.class);
    }
}
