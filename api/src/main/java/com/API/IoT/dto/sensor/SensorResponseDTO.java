package com.API.IoT.dto.sensor;

import java.sql.Timestamp;

import com.API.IoT.dto.system.SystemResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SensorResponseDTO {
    private long id;
    private SystemResponseDTO system;
    private Timestamp createdAt;
    private float ph;
    private int waterTemperature;
    private int ambientTemperature;
    private int humidity;
    private float floatLevel;
}
