package ru.ixlax.TodoWebApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ixlax.TodoWebApp.models.task.Comment;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    public Long countByTaskId(Long id);

    public List<Comment> findAllByTaskId(Long id);

    public Optional<Comment> findByIdAndTaskId(Long commentId,Long taskId);

}
