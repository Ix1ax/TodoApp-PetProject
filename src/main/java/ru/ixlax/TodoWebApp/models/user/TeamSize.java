package ru.ixlax.TodoWebApp.models.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "team_size")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"users"})
public class TeamSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size")
    private String size;

    @OneToMany(mappedBy = "teamSize", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<User> users;

}
