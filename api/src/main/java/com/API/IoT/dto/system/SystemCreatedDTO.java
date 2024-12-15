package com.API.IoT.dto.system;

import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemCreatedDTO {
    @NotBlank
    private String userId;

    @NotBlank
    private int status;

    @NotBlank
    private Timestamp ligthOn;

    @NotBlank
    private Timestamp ligthOff;
}
