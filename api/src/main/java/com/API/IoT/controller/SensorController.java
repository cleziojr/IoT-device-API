package com.API.IoT.controller;

import com.API.IoT.dto.mapper.SensorMapper;
import com.API.IoT.dto.sensor.SensorCreateDTO;
import com.API.IoT.dto.sensor.SensorResponseDTO;
import com.API.IoT.entity.SensorEntity;
import com.API.IoT.service.SensorService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid SensorCreateDTO sensorCreateDTO) {
        sensorService.save(sensorCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> findById(@PathVariable Long id) {
        SensorEntity sensor = sensorService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(SensorMapper.toResponseDTO(sensor));
    }

    @GetMapping
    public ResponseEntity<List<SensorResponseDTO>> findAll() {
        List<SensorResponseDTO> sensors = SensorMapper.toListResponseDTO(
                sensorService.findAll()
        );

        return ResponseEntity.status(HttpStatus.OK).body(sensors);
    }
}
