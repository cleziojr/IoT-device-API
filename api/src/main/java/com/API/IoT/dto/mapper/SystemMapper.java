package com.API.IoT.dto.mapper;

import com.API.IoT.dto.system.SystemCreatedDTO;
import com.API.IoT.dto.system.SystemResponseDTO;
import com.API.IoT.entity.SystemEntity;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class SystemMapper {
    public static SystemResponseDTO toResponseDTO(SystemEntity systemEntity) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(systemEntity, SystemResponseDTO.class);
    }

    public static SystemEntity toSystem(SystemCreatedDTO systemCreatedDTO) {
        PropertyMap<SystemCreatedDTO, SystemEntity> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);

        return modelMapper.map(systemCreatedDTO, SystemEntity.class);
    }

    public static List<SystemResponseDTO> toListResponseDTO(List<SystemEntity> systems) {
        return systems.stream()
                    .map(SystemMapper::toResponseDTO)
                    .collect(Collectors.toList());
    }
}
