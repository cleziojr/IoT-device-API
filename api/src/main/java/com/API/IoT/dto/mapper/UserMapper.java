package com.API.IoT.dto.mapper;

import com.API.IoT.dto.user.UserCreateDTO;
import com.API.IoT.dto.user.UserResponseDTO;
import com.API.IoT.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponseDTO toResponseDTO(UserEntity userEntity) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    public static UserEntity toUser(UserCreateDTO userCreateDTO) {
        PropertyMap<UserCreateDTO, UserEntity> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);

        return modelMapper.map(userCreateDTO, UserEntity.class);
    }

    public static List<UserResponseDTO> toListResponseDTO(List<UserEntity> users) {
       return users.stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
