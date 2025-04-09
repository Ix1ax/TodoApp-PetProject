package ru.ixlax.TodoWebApp.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ixlax.TodoWebApp.dto.request.CommentRequest;
import ru.ixlax.TodoWebApp.dto.response.CommentResponse;
import ru.ixlax.TodoWebApp.exceptions.CommentNotFoundException;
import ru.ixlax.TodoWebApp.exceptions.TaskNotFoundException;
import ru.ixlax.TodoWebApp.services.CommentService;

import java.util.HashMap;
import java.util.List;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/tasks/{taskId}/comments")
    public ResponseEntity<List<CommentResponse>> getAllCommentsForCurrentTask(@PathVariable("taskId") Long taskId) {
        try {
            List<CommentResponse> comments = commentService.getAllCommentsForCurrentTask(taskId);
            return ResponseEntity.status(HttpStatus.OK).body(comments);
        } catch (TaskNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/tasks/{taskId}/comments")
    public ResponseEntity<?> saveComment(@PathVariable("taskId") Long taskId, Principal principal, @Valid @RequestBody CommentRequest commentRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorResponse = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errorResponse.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        try {
            CommentResponse commentResponse = commentService.saveCommentForCurrentTask(taskId,principal,commentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
        } catch(TaskNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

    @DeleteMapping("/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("taskId") Long taskId, @PathVariable("commentId") Long commentId, Principal principal) {
        Boolean deleted = commentService.deleteComment(commentId,taskId);
        if(!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
