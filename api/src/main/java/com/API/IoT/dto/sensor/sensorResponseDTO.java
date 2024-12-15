package com.API.IoT.dto.sensor;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class sensorResponseDTO {
    private long id;
    private Timestamp createdAt;
}
