package com.API.IoT.service;

import com.API.IoT.dto.mapper.SensorMapper;
import com.API.IoT.dto.sensor.SensorCreateDTO;
import com.API.IoT.dto.sensor.SensorResponseDTO;
import com.API.IoT.entity.SensorEntity;
import com.API.IoT.exception.EntityNotFoundException;
import com.API.IoT.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SensorService {
    @Autowired
    private SensorRepository sensorRepository;

    @Transactional
    public void save(SensorCreateDTO sensorCreateDTO) {
        SensorEntity sensorEntity = SensorMapper.toSensorEntity(sensorCreateDTO);
        sensorRepository.save(sensorEntity);
    }

    @Transactional(readOnly = true)
    public List<SensorEntity> findAll() {
        return sensorRepository.findAll();
    }

    @Transactional
    public SensorEntity findById(Long id) {
        return sensorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Sensor não existe")
        );
    }
}
