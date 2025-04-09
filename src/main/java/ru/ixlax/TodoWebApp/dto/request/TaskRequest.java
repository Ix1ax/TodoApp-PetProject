package ru.ixlax.TodoWebApp.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class TaskRequest {

    @NotBlank(message = "Поле Имя Задачи не может быть пустое")
    @Size(min = 3, max = 255, message = "Имя Задачи должно быть от 3 до 255 символов")
    private String taskName;
    @Size(max = 255, message = "Максимальное Описание Задачи должно быть 255 символов")
    private String description;
    private Priority priority;
    private LocalDate dateOfEnd;

}
