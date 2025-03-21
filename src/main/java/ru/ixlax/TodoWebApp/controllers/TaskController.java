package ru.ixlax.TodoWebApp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class TaskController {

//    @GetMapping("/tasks")
//    public String userAccess(Principal principal) {
//
//    }

//    @GetMapping("/test")
//    private String test() {
//        return "You are USER";
//    }

}
