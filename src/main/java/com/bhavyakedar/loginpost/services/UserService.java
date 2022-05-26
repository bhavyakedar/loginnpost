package com.bhavyakedar.loginpost.services;

import com.bhavyakedar.loginpost.LoginpostApplication;
import com.bhavyakedar.loginpost.repositories.UserRepository;
import com.bhavyakedar.loginpost.components.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    public static final int EMAIL_ALREADY_TAKEN = 101;
    public static final int USERNAME_ALREADY_TAKEN = 202;
    public static final int USER_CREATED_SUCCESSFULLY = 999;

    public static final int NO_SUCH_USERNAME = 555;

    public static final int PASSWORD_INCORRECT = 420;

    public static final int AUTHENTICATION_SUCCESS = 343;
    private final UserRepository userRepository;

    private CommentService commentService;

    private BCryptPasswordEncoder passwordEncoder;
    private final MongoTemplate mongoTemplate;

    public UserService(UserRepository userRepository, MongoTemplate mongoTemplate, CommentService commentService)
    {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
        this.commentService = commentService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public int createNewUser(User user)
    {
        Query queryEmail = new Query();
        queryEmail.addCriteria(Criteria.where("email").is(user.getEmail()));
        List<User> matchingUsersEmail = mongoTemplate.find(queryEmail, User.class);

        Query queryUsername = new Query();
        queryUsername.addCriteria(Criteria.where("username").is(user.getUsername()));
        List<User> matchingUsersUsername = mongoTemplate.find(queryUsername, User.class);

        if(matchingUsersEmail.size() > 0)
        {
            return EMAIL_ALREADY_TAKEN;
        }
        else if(matchingUsersUsername.size() > 0)
        {
            return USERNAME_ALREADY_TAKEN;
        }
        else
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.insert(user);
            return USER_CREATED_SUCCESSFULLY;
        }
    }

    public int authenticateUser(String username, String password)
    {
        Query queryUsername = new Query();
        queryUsername.addCriteria(Criteria.where("username").is(username));
        List<User> matchingUserUsername = mongoTemplate.find(queryUsername, User.class);

        if(matchingUserUsername.size() == 0) return NO_SUCH_USERNAME;

        else
        {
            User matchedUser = matchingUserUsername.get(0);
            boolean passwordMatches = passwordEncoder.matches(password, matchedUser.getPassword());

            if(passwordMatches)
            {
                LoginpostApplication.currentUser = matchedUser;
                return AUTHENTICATION_SUCCESS;
            }
            else
            {
                return PASSWORD_INCORRECT;
            }
        }
    }

    public User getUserData(String username)
    {
        Query queryUsername = new Query();
        queryUsername.addCriteria(Criteria.where("username").is(username));
        User matchedUser = mongoTemplate.findOne(queryUsername, User.class);
        return matchedUser;
    }

    public User changeName(String username, String newName)
    {
        Query queryUsername = new Query();
        queryUsername.addCriteria(Criteria.where("username").is(username));
        Update update = new Update();
        update.set("name",newName);
        User matchedUser = mongoTemplate.findAndModify(queryUsername, update, User.class);
        return matchedUser;
    }

    public void deleteUserAccount(String username)
    {
        commentService.deleteUserComments(username);
        Query queryUsername = new Query();
        queryUsername.addCriteria(Criteria.where("username").is(username));
        mongoTemplate.findAllAndRemove(queryUsername, User.class);
        LoginpostApplication.currentUser = null;
    }
}
