package ru.ixlax.TodoWebApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ixlax.TodoWebApp.dto.response.UserRoleResponse;
import ru.ixlax.TodoWebApp.mappers.UserRoleMapper;
import ru.ixlax.TodoWebApp.models.user.UserRole;
import ru.ixlax.TodoWebApp.repositories.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;

    public List<UserRoleResponse> getAllUserRoles() {
        List<UserRole> userRoles = userRoleRepository.findAll();

        return mapToDtoList(userRoles);
    }

    private List<UserRoleResponse> mapToDtoList(List<UserRole> userRoles) {
        if(userRoles.isEmpty()) {
            return new ArrayList<>();
        }

        return userRoles.stream()
                .map(userRoleMapper::toUserRoleDTO)
                .toList();

    }

    public UserRole getUserRoleById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserRole ID: " + id));
    }

}
