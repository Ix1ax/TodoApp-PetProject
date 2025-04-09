package ru.ixlax.TodoWebApp.mappers;

import org.springframework.stereotype.Component;
import ru.ixlax.TodoWebApp.dto.response.UserResponse;
import ru.ixlax.TodoWebApp.dto.response.UserShortResponse;
import ru.ixlax.TodoWebApp.models.user.User;

import java.security.Principal;

@Component
public class UserMapper {

    public UserShortResponse toUserShortResponse(User user) {
        return new UserShortResponse(
                user.getName()
                /*
                TODO
                 */
        );
    }

}
