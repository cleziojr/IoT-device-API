package com.API.IoT.service;

import com.API.IoT.dto.mapper.UserMapper;
import com.API.IoT.dto.user.UserCreateDTO;
import com.API.IoT.dto.user.UserResponseDTO;
import com.API.IoT.entity.UserEntity;
import com.API.IoT.exception.EntityNotFoundException;
import com.API.IoT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(UserCreateDTO userCreateDTO) {
        userRepository.save(UserMapper.toUser(userCreateDTO));
    }

    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Usuário não existe")
        );
    }

    @Transactional(readOnly = true)
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Usuário não existe")
        );
    }
}
