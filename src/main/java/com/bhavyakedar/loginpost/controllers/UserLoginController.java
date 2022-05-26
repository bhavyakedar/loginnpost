package com.bhavyakedar.loginpost.controllers;

import com.bhavyakedar.loginpost.LoginpostApplication;
import com.bhavyakedar.loginpost.model.LoginUserStatus;
import com.bhavyakedar.loginpost.model.UserLoginModel;
import com.bhavyakedar.loginpost.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class UserLoginController {

    private final UserService userService;

    @GetMapping("/")
    public ModelAndView redirectToLogin(){
        return new ModelAndView("redirect:/loginPage");
    }

    @GetMapping("/loginPage")
    public ModelAndView userLogin()
    {
        if(LoginpostApplication.currentUser !=  null) {
            return new ModelAndView("redirect:/comments");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @PostMapping("/loginPage")
    public LoginUserStatus userLoginRequest(@RequestBody UserLoginModel userLoginModel)
    {
        String username = userLoginModel.getUsername();
        String password = userLoginModel.getPassword();

        if(LoginpostApplication.currentUser != null)
        {
            return new LoginUserStatus("You are already logged in!\nPlease go to the comments tab.");
        }

        int status = userService.authenticateUser(username, password);
        if(status == UserService.NO_SUCH_USERNAME)
        {
            return new LoginUserStatus("No such username exists!");
        }
        else if(status == UserService.PASSWORD_INCORRECT)
        {
            return new LoginUserStatus("Entered Password is incorrect!");
        }
        else
        {
            return new LoginUserStatus("Login successful!\nPlease go to the comments tab.");
        }
    }
}
