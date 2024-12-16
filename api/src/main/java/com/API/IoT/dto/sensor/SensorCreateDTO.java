package com.API.IoT.dto.sensor;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorCreateDTO {
    @NotNull
    private float ph;

    @NotNull
    private int waterTemperature;

    @NotNull
    private int ambientTemperature;

    @NotNull
    private int humidity;

    @NotNull
    private float floatLevel;
}
