package ru.ixlax.TodoWebApp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    @NotBlank(message = "Поле Email должно быть заполнено")
    @Email
    private String email;
    @NotBlank(message = "Поле Пароль должно быть заполено")
    private String password;

}
