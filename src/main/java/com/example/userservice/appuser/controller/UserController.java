package com.example.userservice.appuser.controller;

import com.example.userservice.appuser.model.AppUser;
import com.example.userservice.appuser.model.UserRole;
import com.example.userservice.appuser.service.UserService;
import com.example.userservice.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@RestController
@RequestMapping
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/")
    public String getUsers(@AuthenticationPrincipal OAuth2User principal){
        if (principal != null){
            userService.signUpUser(new AppUser(principal.getAttribute("given_name"), principal.getAttribute("family_name"),
                    principal.getAttribute("email"), principal.getAttribute("at_hash"), UserRole.USER));
        }

        return "Successfully registrated!\nYour username: " + principal.getAttribute("email") + "\nYour password: " + principal.getAttribute("at_hash");
        //return new RedirectView("http://localhost:3000");
    }

}
