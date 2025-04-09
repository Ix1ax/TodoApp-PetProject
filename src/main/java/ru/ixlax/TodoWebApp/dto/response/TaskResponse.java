package ru.ixlax.TodoWebApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ixlax.TodoWebApp.models.task.Priority;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private Long id;
    private String taskName;
    private String description;
    private Priority priority;
    private LocalDateTime updateAt;
    private LocalDate dateOfEnd;
    private Long countComments;

}
