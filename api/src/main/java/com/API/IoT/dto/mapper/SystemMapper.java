package com.API.IoT.dto.mapper;

import com.API.IoT.dto.system.SystemCreateDTO;
import com.API.IoT.dto.system.SystemResponseDTO;
import com.API.IoT.entity.SystemEntity;
import com.API.IoT.entity.UserEntity;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class SystemMapper {
    public static SystemResponseDTO toResponseDTO(SystemEntity systemEntity) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(systemEntity, SystemResponseDTO.class);
    }

    public static SystemEntity toSystem(SystemCreateDTO systemCreatedDTO, UserEntity user) {
        PropertyMap<SystemCreateDTO, SystemEntity> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setId(null);
                map(source.getUserId(), destination.getUser());
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);

        SystemEntity systemEntity = modelMapper.map(systemCreatedDTO, SystemEntity.class);

        systemEntity.setUser(user);

        return systemEntity;
    }

    public static List<SystemResponseDTO> toListResponseDTO(List<SystemEntity> systems) {
        return systems.stream()
                    .map(SystemMapper::toResponseDTO)
                    .collect(Collectors.toList());
    }
}
