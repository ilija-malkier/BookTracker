package com.booktracker.booktracker.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/user")
    public String getUser(@AuthenticationPrincipal OAuth2User oAuth2User){
        return oAuth2User.getName();
    }
}
