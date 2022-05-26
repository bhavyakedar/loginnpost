package com.bhavyakedar.loginpost.controllers;

import com.bhavyakedar.loginpost.LoginpostApplication;
import com.bhavyakedar.loginpost.components.User;
import com.bhavyakedar.loginpost.model.ChangeNameModel;
import com.bhavyakedar.loginpost.model.CreateUserStatus;
import com.bhavyakedar.loginpost.model.UserCreateModel;
import com.bhavyakedar.loginpost.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class UserDataController {

    private final UserService userService;

    @GetMapping("/createUser")
    public ModelAndView createUserPage()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createUser.html");
        return modelAndView;
    }

    @PostMapping("/createUser")
    public CreateUserStatus requestCreateUser(@RequestBody UserCreateModel userCreateModel)
    {
        String username = userCreateModel.getUsername();
        String name = userCreateModel.getName();
        String password = userCreateModel.getPassword();
        String email = userCreateModel.getEmail();
        User user = new User(username, name, email, LocalDateTime.now(), password);
        int status = userService.createNewUser(user);
        if(status == UserService.EMAIL_ALREADY_TAKEN)
        {
            CreateUserStatus error = new CreateUserStatus("Email Already Taken!", " ", " ");
            return error;
        }
        else if(status == UserService.USERNAME_ALREADY_TAKEN)
        {
            CreateUserStatus error = new CreateUserStatus(" ", "Username Already Taken!", " ");
            return error;
        }
        else
        {
            CreateUserStatus error = new CreateUserStatus(" ", " ", "User created successfully!");
            return error;
        }
    }

    @GetMapping("/myProfileData")
    public User myProfileData()
    {
        return userService.getUserData(LoginpostApplication.currentUser.getUsername());
    }

    @PostMapping("/changeName")
    public User changeName(@RequestBody ChangeNameModel changeNameModel)
    {
        String newName = changeNameModel.getName();
        return userService.changeName(LoginpostApplication.currentUser.getUsername(), newName);
    }

    @GetMapping("/deleteAccount")
    public void deleteAccount()
    {
        userService.deleteUserAccount(LoginpostApplication.currentUser.getUsername());
    }
}
