package ru.ixlax.TodoWebApp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ixlax.TodoWebApp.models.user.User;
import ru.ixlax.TodoWebApp.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }



}
