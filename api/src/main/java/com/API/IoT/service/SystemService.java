package com.API.IoT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.API.IoT.dto.mapper.SystemMapper;
import com.API.IoT.dto.system.SystemCreateDTO;
import com.API.IoT.entity.SystemEntity;
import com.API.IoT.entity.UserEntity;
import com.API.IoT.exception.EntityNotFoundException;
import com.API.IoT.repository.SystemRepository;

@Service
public class SystemService {
    @Autowired
    private SystemRepository systemRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void save(SystemCreateDTO systemCreateDTO) {
        UserEntity user = userService.findById(systemCreateDTO.getUserId());
        
        SystemEntity systemEntity = SystemMapper.toSystem(systemCreateDTO, user);
    
        systemRepository.save(systemEntity);
    }

    @Transactional(readOnly = true)
    public List<SystemEntity> findAll() {
        return systemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SystemEntity findById(Long id) {
        return systemRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Usuário não existe")
        );
    }
}
