package ru.ixlax.TodoWebApp.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ixlax.TodoWebApp.dto.request.SignInRequest;
import ru.ixlax.TodoWebApp.dto.request.SignUpRequest;
import ru.ixlax.TodoWebApp.services.UserService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class SecurityController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorResponse = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errorResponse.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        if(userService.isEmailAlreadyExists(signUpRequest.getEmail())) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("email", "Данная почта уже используется");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        userService.signUpUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь успешно зарегистрирован");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest signInRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorResponse = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errorResponse.put(error.getField(), error.getDefaultMessage()));
        }
        return userService.signInUser(signInRequest);
    }


}
