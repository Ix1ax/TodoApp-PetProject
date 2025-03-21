package ru.ixlax.TodoWebApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ixlax.TodoWebApp.dto.UserRoleRequest;
import ru.ixlax.TodoWebApp.models.user.UserRole;
import ru.ixlax.TodoWebApp.repositories.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public List<UserRoleRequest> getAllUserRoles() {
        List<UserRole> userRoles = userRoleRepository.findAll();

        return mapToDtoList(userRoles);
    }

    private List<UserRoleRequest> mapToDtoList(List<UserRole> userRoles) {
        if(userRoles.isEmpty()) {
            return new ArrayList<>();
        }

        return userRoles.stream()
                .map(userRole -> new UserRoleRequest(userRole.getId(),userRole.getUserRole()))
                .toList();

    }

    public UserRole getUserRoleById(Integer id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserRole ID: " + id));
    }

}
