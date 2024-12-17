package com.API.IoT.dto.system;
import com.API.IoT.entity.UserEntity;
import com.API.IoT.enums.SystemStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemResponseDTO {
    private Long id;
    private UserEntity userId;
    private Timestamp  ligthOn;
    private Timestamp  ligthOff;
    private SystemStatusEnum status;
}
