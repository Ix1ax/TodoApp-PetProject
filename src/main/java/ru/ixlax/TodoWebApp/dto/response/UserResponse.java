package ru.ixlax.TodoWebApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String phoneNumber;
    private Integer age;
    private Long teamSize;
    private List<Long> userActivities;
    private Long userRole;
    private String registerAt;
    private String email;
    private String role;

}
