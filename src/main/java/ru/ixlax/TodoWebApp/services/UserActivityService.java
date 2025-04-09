package ru.ixlax.TodoWebApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ixlax.TodoWebApp.dto.response.UserActivityResponse;
import ru.ixlax.TodoWebApp.mappers.UserActivityMapper;
import ru.ixlax.TodoWebApp.models.user.UserActivity;
import ru.ixlax.TodoWebApp.repositories.UserActivityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;
    private final UserActivityMapper userActivityMapper;

    public List<UserActivityResponse> getAllUserActivities() {

        List<UserActivity> userActivities = userActivityRepository.findAll();
        return mapToDtoList(userActivities);
    }

    private List<UserActivityResponse> mapToDtoList(List<UserActivity> userActivities) {

        if(userActivities.isEmpty()) {
            return new ArrayList<>();
        }

        return userActivities.stream()
                .map(userActivityMapper::toUserActivityDTO)
                .toList();
    }

    public UserActivity getUserActivityById(Long id) {
        return userActivityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserActivity ID: " + id));
    }

}
