package ru.ixlax.TodoWebApp.mappers;

import org.springframework.stereotype.Component;
import ru.ixlax.TodoWebApp.dto.request.CommentRequest;
import ru.ixlax.TodoWebApp.dto.response.CommentResponse;
import ru.ixlax.TodoWebApp.dto.response.UserShortResponse;
import ru.ixlax.TodoWebApp.models.task.Comment;
import ru.ixlax.TodoWebApp.services.TaskInfoService;

@Component
public class CommentMapper {

    public Comment toComment(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setDescription(commentRequest.getDescription());

        /*
        TODO
         */

        return comment;
    }

    public CommentResponse toCommentResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getDescription(),
                comment.getUpdatedAt(),
                new UserShortResponse(
                        comment.getUser().getName()
                )
        );
    }

}
