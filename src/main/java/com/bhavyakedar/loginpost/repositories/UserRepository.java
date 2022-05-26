package com.bhavyakedar.loginpost.repositories;

import com.bhavyakedar.loginpost.components.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
