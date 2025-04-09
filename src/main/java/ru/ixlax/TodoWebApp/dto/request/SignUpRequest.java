package ru.ixlax.TodoWebApp.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "Поле Имя должно быть заполнено")
    @Size(min = 1,max = 30,message = "Поле Имя должно содержать меньше 30 символов")
    private String name;
    @NotBlank(message = "Поле Номер Телефона должно быть заполнено")
    @Pattern(regexp = "^(\\+7|8)[0-9]{10}$", message = "Неверный формат номера телефона.")
    private String phoneNumber;
    @NotNull(message = "Поле Возраст должно быть заполнено ")
    @Min(value = 1, message = "Минимальный возраст 1")
    @Max(value = 100,message = "Максимальный возраст 100")
    private Integer age;
    @NotNull(message = "Поле Количество Людей В Команде должно быть заполнено")
    private Long teamSize;
    @NotNull(message = "Пол Активности должно быть заполнено")
    private List<Long> userActivities;
    @NotNull(message = "Поле Роль Пользователя должно быть заполнено")
    private Long userRole;
    @NotBlank(message = "Поле Email должно быть заполнено")
    @Email(message = "Поле Email должно иметь формат адреса электронной почты")
    private String email;
    @NotBlank(message = "Поле Пароль должно быть заполено")
    private String password;
}
