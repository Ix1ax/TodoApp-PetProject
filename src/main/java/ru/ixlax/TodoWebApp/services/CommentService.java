package ru.ixlax.TodoWebApp.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ixlax.TodoWebApp.dto.request.CommentRequest;
import ru.ixlax.TodoWebApp.dto.response.CommentResponse;
import ru.ixlax.TodoWebApp.exceptions.TaskNotFoundException;
import ru.ixlax.TodoWebApp.mappers.CommentMapper;
import ru.ixlax.TodoWebApp.models.task.Comment;
import ru.ixlax.TodoWebApp.models.task.Task;
import ru.ixlax.TodoWebApp.models.user.User;
import ru.ixlax.TodoWebApp.repositories.CommentRepository;
import ru.ixlax.TodoWebApp.repositories.TaskRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final TaskRepository taskRepository;

    @Transactional
    public CommentResponse saveCommentForCurrentTask(Long taskId, Principal principal, CommentRequest commentRequest) {
        User user = userService.getUserByEmail(principal.getName());
        Comment comment = commentMapper.toComment(commentRequest);
        Task task = taskRepository.findTaskByIdAndUserEmail(taskId,principal.getName())
                .orElseThrow(() -> new TaskNotFoundException("Задача не найдена " + taskId));
        comment.setTask(task);
        comment.setUser(user);
        comment.setUpdatedAt(LocalDateTime.now());
        return commentMapper.toCommentResponse(commentRepository.save(comment));
    }

    public List<CommentResponse> getAllCommentsForCurrentTask(Long taskId) {
        Task task = taskRepository.findTaskByIdWithComments(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Задача с комментариями не найдена " + taskId));
        return task.getComments()
                .stream()
                .map(commentMapper::toCommentResponse)
                .toList();
    }

    @Transactional
    public Boolean deleteComment(Long taskId, Long commentId) {
        return commentRepository.findByIdAndTaskId(commentId,taskId)

                .map(comment -> {
                    log.debug(comment.toString());
                    User user = comment.getUser();
                    user.getComments().remove(comment);

                    Task task = comment.getTask();
                    task.getComments().remove(comment);

                    commentRepository.delete(comment);
                    return true;
                })
                .orElse(false);
    }
}
