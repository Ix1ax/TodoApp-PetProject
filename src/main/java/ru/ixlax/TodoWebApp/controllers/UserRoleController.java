package ru.ixlax.TodoWebApp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ixlax.TodoWebApp.services.UserRoleService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;

    @GetMapping("/user-roles")
    public ResponseEntity<?> getAllUserRoles() {
        return ResponseEntity.ok(userRoleService.getAllUserRoles());
    }


}
