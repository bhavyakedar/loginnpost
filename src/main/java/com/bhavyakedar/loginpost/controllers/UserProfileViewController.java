package com.bhavyakedar.loginpost.controllers;

import com.bhavyakedar.loginpost.LoginpostApplication;
import com.bhavyakedar.loginpost.components.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class UserProfileViewController {

    @GetMapping("/myProfile")
    public ModelAndView viewMyProfile()
    {
        if(LoginpostApplication.currentUser == null)
        {
            return new ModelAndView("redirect:/loginPage");
        }
        else
        {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("myProfile.html");
            return modelAndView;
        }
    }
}
