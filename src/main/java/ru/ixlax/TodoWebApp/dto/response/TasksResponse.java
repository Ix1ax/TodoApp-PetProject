package ru.ixlax.TodoWebApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ixlax.TodoWebApp.models.task.Priority;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TasksResponse {

    private Long id;
    private String taskName;
    private String description;
    private Priority priority;
    private LocalDate dateOfEnd;

}
