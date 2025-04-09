package ru.ixlax.TodoWebApp.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    @NotBlank(message = "Поле Комментария должно быть заполнено")
    @Max(value = 255, message = "Поле комментария может содержать максимум 255 символов")
    private String description;

    /*
    TODO
     */

}
