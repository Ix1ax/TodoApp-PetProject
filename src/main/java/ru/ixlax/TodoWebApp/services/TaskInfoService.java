package ru.ixlax.TodoWebApp.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.ixlax.TodoWebApp.models.task.Task;
import ru.ixlax.TodoWebApp.repositories.CommentRepository;
import ru.ixlax.TodoWebApp.repositories.TaskRepository;

import java.security.Principal;

@Service
@AllArgsConstructor
public class TaskInfoService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    public Long getCountCommentsForTask(Long id) {
        return commentRepository.countByTaskId(id);
    }

}
