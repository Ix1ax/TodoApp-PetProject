package ru.ixlax.TodoWebApp.models.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ixlax.TodoWebApp.models.user.User;

import java.time.LocalDateTime;

/*
    Айди пользователя
    Дата написания комментария
    Сам комментарий(может плюсом идти фотка) (Обязательно)
    Айди задачи
*/

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;
    @ManyToOne()
    @JoinColumn(name = "task_id")
    private Task task;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

}
