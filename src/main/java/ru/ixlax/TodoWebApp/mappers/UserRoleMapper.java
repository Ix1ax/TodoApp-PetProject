package ru.ixlax.TodoWebApp.mappers;

import org.springframework.stereotype.Component;
import ru.ixlax.TodoWebApp.dto.response.UserRoleResponse;
import ru.ixlax.TodoWebApp.models.user.UserRole;

@Component
public class UserRoleMapper {

    public UserRoleResponse toUserRoleDTO(UserRole userRole) {
        return new UserRoleResponse(userRole.getId(), userRole.getUserRole());
    }

}
