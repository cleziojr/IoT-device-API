package com.API.IoT.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {
    @NotBlank
    private String name;
    @NotBlank
    @Email(message = "formate de ee-mail inválido")
    private String email;
    @NotBlank
    @Size(min = 5)
    private String password;
}
