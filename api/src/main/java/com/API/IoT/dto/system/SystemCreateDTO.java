package com.API.IoT.dto.system;

import jakarta.validation.constraints.Positive;

import java.time.LocalTime;

import com.API.IoT.enums.SystemStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemCreateDTO {
    @Positive
    private Long userId;

    private SystemStatusEnum status;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime lightOn;

    @JsonFormat(pattern = "HH:mm") 
    private LocalTime lightOff;
}
