package ru.ixlax.TodoWebApp.services;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ixlax.TodoWebApp.dto.request.TaskRequest;
import ru.ixlax.TodoWebApp.dto.response.TaskResponse;
import ru.ixlax.TodoWebApp.dto.response.TasksResponse;
import ru.ixlax.TodoWebApp.mappers.TaskMapper;
import ru.ixlax.TodoWebApp.models.task.Task;
import ru.ixlax.TodoWebApp.repositories.TaskRepository;

import java.security.Principal;
import java.time.LocalDateTime;

import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final UserService userService;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskInfoService taskInfoService;

    @Transactional
    public TasksResponse saveTask(Principal principal, TaskRequest taskRequest) {
        Task task = taskMapper.toTask(taskRequest);
        task.setUpdateAt(LocalDateTime.now());
        task.setUser(userService.getUserByEmail(principal.getName()));
        userService.getUserByEmail(principal.getName()).getTasks().add(task);
        return taskMapper.toTasksResponse(taskRepository.save(task));
    }

    public List<TasksResponse> getAllUserTask(Principal principal) {
        List<Task> tasks = taskRepository.findTasksByUserEmail(principal.getName()).
                orElse(Collections.emptyList());
        return tasks.stream()
                .map(taskMapper::toTasksResponse)
                .toList();
    }


    public TaskResponse getUserTask(Long id, Principal principal) {
        return taskRepository.findTaskByIdAndUserEmail(id, principal.getName())
                .map(task -> {
                    TaskResponse taskResponse = taskMapper.toTaskResponse(task);
                    taskResponse.setCountComments(taskInfoService.getCountCommentsForTask(id));
                    return taskResponse;
                })
                .orElse(null);
    }

    @Transactional
    public Boolean deleteTaskById(Principal principal, Long taskId) {
        return taskRepository.findTaskByIdAndUserEmail(taskId,principal.getName())
                .map(task -> {
                    userService.getUserByEmail(principal.getName()).getTasks().remove(task);
                    taskRepository.delete(task);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public TaskResponse updateTask(Long taskId, Principal principal, @Valid TaskRequest taskRequest) {
        return taskRepository.findTaskByIdAndUserEmail(taskId, principal.getName())
                .map(task -> {
                    task.setTaskName(taskRequest.getTaskName());
                    task.setDescription(taskRequest.getDescription());
                    task.setPriority(taskRequest.getPriority());
                    task.setUpdateAt(LocalDateTime.now());
                    TaskResponse taskResponse = taskMapper.toTaskResponse(taskRepository.save(task));
                    taskResponse.setCountComments(taskInfoService.getCountCommentsForTask(taskId));
                    return taskResponse;
                })
                .orElse(null);
    }
}
