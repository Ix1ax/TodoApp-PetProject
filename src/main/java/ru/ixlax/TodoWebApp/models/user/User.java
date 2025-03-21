package ru.ixlax.TodoWebApp.models.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "age", nullable = false)
    private Integer age;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_size_id")
    private TeamSize teamSize;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_user_activity",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_activity_id")
    )
    private List<UserActivity> userActivities;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;
    @Column(name = "register_at", nullable = false)
    private LocalDateTime registerAt;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}
