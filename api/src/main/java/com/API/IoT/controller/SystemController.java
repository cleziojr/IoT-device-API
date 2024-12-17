package com.API.IoT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.IoT.dto.mapper.SystemMapper;
import com.API.IoT.dto.system.SystemCreateDTO;
import com.API.IoT.dto.system.SystemResponseDTO;
import com.API.IoT.entity.SystemEntity;
import com.API.IoT.service.SystemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/systems")
public class SystemController {
    @Autowired
    private SystemService systemService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid SystemCreateDTO systemCreatedDTO) {
        systemService.save(systemCreatedDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemResponseDTO> findById(@PathVariable Long id) {
        SystemEntity system = systemService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(SystemMapper.toResponseDTO(system));
    }

    @GetMapping
    public ResponseEntity<List<SystemResponseDTO>> findAll() {
        List<SystemEntity> systemEntities = systemService.findAll();
        List<SystemResponseDTO> systemResponse = SystemMapper.toListResponseDTO(systemEntities);

        return ResponseEntity.status(HttpStatus.OK).body(systemResponse);
    }
}
