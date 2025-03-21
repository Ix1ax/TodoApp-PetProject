package ru.ixlax.TodoWebApp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ixlax.TodoWebApp.services.UserActivityService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserActivityController {

    private final UserActivityService userActivityService;

    @GetMapping("/user-activities")
    public ResponseEntity<?> getAllUserActivities() {
        return ResponseEntity.ok(userActivityService.getAllUserActivities());
    }

}
