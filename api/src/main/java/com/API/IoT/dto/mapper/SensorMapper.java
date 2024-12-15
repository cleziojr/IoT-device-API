package com.API.IoT.dto.mapper;

import com.API.IoT.dto.sensor.SensorCreateDTO;
import com.API.IoT.dto.sensor.SensorResponseDTO;
import com.API.IoT.entity.SensorEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class SensorMapper {
    public static SensorResponseDTO toResponseDTO(SensorEntity sensorEntity) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(sensorEntity, SensorResponseDTO.class);
    }

    public static SensorEntity toSensor(SensorCreateDTO sensorCreateDTO) {
        PropertyMap<SensorCreateDTO, SensorEntity> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);

        return modelMapper.map(sensorCreateDTO, SensorEntity.class);
    }

    public static List<SensorResponseDTO> toListResponseDTO(List<SensorEntity> sensors) {
       return sensors.stream()
                .map(SensorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
