package ru.ixlax.TodoWebApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ixlax.TodoWebApp.dto.UserActivityRequest;
import ru.ixlax.TodoWebApp.models.user.UserActivity;
import ru.ixlax.TodoWebApp.repositories.UserActivityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;

    public List<UserActivityRequest> getAllUserActivities() {

        List<UserActivity> userActivities = userActivityRepository.findAll();

        return mapToDtoList(userActivities);
    }

    private List<UserActivityRequest> mapToDtoList(List<UserActivity> userActivities) {

        if(userActivities.isEmpty()) {
            return new ArrayList<>();
        }

        return userActivities.stream()
                .map(userActivity -> new UserActivityRequest(userActivity.getId(),userActivity.getUserActivity()))
                .toList();
    }

    public UserActivity getUserActivityById(Integer id) {
        return userActivityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserActivity ID: " + id));
    }

}
