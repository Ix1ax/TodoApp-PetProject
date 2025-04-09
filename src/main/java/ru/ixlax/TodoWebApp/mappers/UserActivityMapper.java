package ru.ixlax.TodoWebApp.mappers;

import org.springframework.stereotype.Component;
import ru.ixlax.TodoWebApp.dto.response.UserActivityResponse;
import ru.ixlax.TodoWebApp.models.user.UserActivity;

@Component
public class UserActivityMapper {

    public UserActivityResponse toUserActivityDTO(UserActivity userActivity) {

        return new UserActivityResponse(userActivity.getId(),userActivity.getUserActivity());

    }

}
