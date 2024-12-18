package com.API.IoT.dto.system;
import com.API.IoT.dto.user.UserResponseDTO;
import com.API.IoT.enums.SystemStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private LocalTime  lightOn;
    private LocalTime  lightOff;
    private SystemStatusEnum status;
    private Timestamp createdAt;
}
