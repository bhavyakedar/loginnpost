package com.bhavyakedar.loginpost.controllers;

import com.bhavyakedar.loginpost.LoginpostApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LogoutController {
    @GetMapping("/logout")
    public ModelAndView logoutUser()
    {
        LoginpostApplication.currentUser = null;
        return new ModelAndView("redirect:/loginPage");
    }
}
