package ru.ixlax.TodoWebApp.models.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;
import ru.ixlax.TodoWebApp.models.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
Задача
    Название (Обязательное поле)
    Описание (Не обязательное)
    Приоритет (Не обязательное)
    Дата обновление, автоматически при создании + обновлении
    Срок выполения необязательно, пользователь сам указывает
    Список комментариев
    Айди пользователя
 */

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task_name", nullable = false)
    private String taskName;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;
    @Column(name = "date_of_end")
    private LocalDate dateOfEnd;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
