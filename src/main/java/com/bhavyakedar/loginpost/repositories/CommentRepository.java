package com.bhavyakedar.loginpost.repositories;

import com.bhavyakedar.loginpost.components.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
