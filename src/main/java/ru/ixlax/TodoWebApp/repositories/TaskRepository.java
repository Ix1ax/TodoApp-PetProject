package ru.ixlax.TodoWebApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ixlax.TodoWebApp.models.task.Task;
import java.util.List;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public Optional<List<Task>> findTasksByUserEmail(String email);

    public Optional<Task> findTaskByIdAndUserEmail(Long id, String email);

    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.comments WHERE t.id = :taskId")
    Optional<Task> findTaskByIdWithComments(@Param("taskId") Long taskId);

}
