package ru.ixlax.TodoWebApp.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ixlax.TodoWebApp.dto.request.TaskRequest;
import ru.ixlax.TodoWebApp.dto.response.TaskResponse;
import ru.ixlax.TodoWebApp.dto.response.TasksResponse;
import ru.ixlax.TodoWebApp.services.TaskService;

import java.util.HashMap;
import java.util.List;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("")
    public ResponseEntity<List<TasksResponse>> allTaskForCurrentUser(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllUserTask(principal));
    }

    @PostMapping("")
    public ResponseEntity<?> saveTask(Principal principal, @Valid @RequestBody TaskRequest taskRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorResponse = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errorResponse.put(error.getField(), error.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(principal, taskRequest));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> currentTaskForCurrentUser(@PathVariable("taskId") Long taskId, Principal principal) {
        TaskResponse currentUserTask = taskService.getUserTask(taskId, principal);
        if(currentUserTask == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(currentUserTask);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable("taskId") Long taskId, Principal principal ,@Valid @RequestBody TaskRequest taskRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorResponse = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errorResponse.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        TaskResponse currentUserTask = taskService.updateTask(taskId,principal,taskRequest);
        if(currentUserTask == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(currentUserTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(Principal principal, @PathVariable("taskId") Long taskId) {
        Boolean deleted = taskService.deleteTaskById(principal, taskId);
        if(!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
