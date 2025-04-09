package ru.ixlax.TodoWebApp.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ixlax.TodoWebApp.dto.request.SignInRequest;
import ru.ixlax.TodoWebApp.dto.request.SignUpRequest;
import ru.ixlax.TodoWebApp.models.user.*;
import ru.ixlax.TodoWebApp.repositories.UserRepository;
import ru.ixlax.TodoWebApp.security.JwtCore;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final TeamSizeService teamSizeService;
    private final UserActivityService userActivityService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Email '%s' not found", email)
                ));
    }

    public boolean isEmailAlreadyExists(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Transactional
    public void signUpUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setAge(signUpRequest.getAge());

        TeamSize teamSize = teamSizeService.getTeamSizeById(signUpRequest.getTeamSize());
        user.setTeamSize(teamSize);

        List<UserActivity> userActivities = signUpRequest.getUserActivities().stream()
                .map(userActivityService::getUserActivityById)
                .collect(Collectors.toList());
        user.setUserActivities(userActivities);

        UserRole userRole = userRoleService.getUserRoleById(signUpRequest.getUserRole());
        user.setUserRole(userRole);

        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setRegisterAt(LocalDateTime.now());

        userRepository.save(user);

        log.info(user.toString());
    }

    public ResponseEntity<?> signInUser(SignInRequest signInRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequest.getEmail(),
                            signInRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            log.warn("Неудачная попытка входа: {}", signInRequest.getEmail());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Неверный логин или пароль");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            log.error("Ошибка аутентификации: ", e);
            return new ResponseEntity<>("Ошибка сервера", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);

        log.info("For {}, JWT = {}", signInRequest.getEmail(), jwt);

        return ResponseEntity.ok(jwt);
    }
}
