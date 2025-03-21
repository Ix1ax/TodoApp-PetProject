package ru.ixlax.TodoWebApp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ixlax.TodoWebApp.services.TeamSizeService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TeamSizeController {

    private final TeamSizeService teamSizeService;

    @GetMapping("/team-sizes")
    public ResponseEntity<?> getAllTeamSizes() {
        return ResponseEntity.ok(teamSizeService.getAllTeamSizes());
    }

}
