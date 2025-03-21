package ru.ixlax.TodoWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;
    private String name;
    private String phoneNumber;
    private Integer age;
    private Integer teamSize;
    private List<Integer> userActivities;
    private Integer userRole;
    private String registerAt;
    private String email;
    private String role;

}
