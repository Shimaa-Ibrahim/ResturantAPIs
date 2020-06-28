package com.RestauarantAPIs.Restaurant.controllers;

import com.RestauarantAPIs.Restaurant.entities.User;
import com.RestauarantAPIs.Restaurant.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/*
 * user sign up
 * mapped by /sign-up
 * */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/auth/sign-up")
    public User signUp(@Valid @RequestBody User user) {
        userService.signUpUser(user);
        return user;
    }


}