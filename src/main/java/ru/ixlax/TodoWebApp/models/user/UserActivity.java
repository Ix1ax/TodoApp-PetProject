package ru.ixlax.TodoWebApp.models.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
/*
Деятельность пользователя
 */
@Entity
@Table(name = "user_activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"users"})
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_activity")
    private String userActivity;

    @ManyToMany(mappedBy = "userActivities", cascade = CascadeType.PERSIST)
    private List<User> users;

}
