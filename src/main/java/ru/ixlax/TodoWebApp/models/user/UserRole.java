package ru.ixlax.TodoWebApp.models.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*
Роль для профиля
 */
@Entity
@Table(name = "user_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"users"})
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_role")
    private String userRole;

    @OneToMany(mappedBy = "userRole", cascade = CascadeType.PERSIST)
    private List<User> users;


}
