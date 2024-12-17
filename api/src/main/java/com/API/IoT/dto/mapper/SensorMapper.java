package com.API.IoT.dto.mapper;

import com.API.IoT.dto.sensor.sensorCreateDTO;
import com.API.IoT.dto.sensor.sensorResponseDTO;
import com.API.IoT.entity.SensorEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class SensorMapper {
    public static sensorResponseDTO toResponseDTO(SensorEntity sensorEntity) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(sensorEntity, sensorResponseDTO.class);
    }

    public static SensorEntity toSensorEntity(sensorCreateDTO sensorCreateDTO) {
        PropertyMap<sensorCreateDTO, SensorEntity> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);

        return modelMapper.map(sensorCreateDTO, SensorEntity.class);
    }

    public static List<sensorResponseDTO> toListResponseDTO(List<SensorEntity> sensors) {
       return sensors.stream()
                .map(SensorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
