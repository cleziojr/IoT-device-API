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

    @Transactional
    public void delete(Long id) {
        sensorRepository.deleteById(id);
    }

    @Transactional
    public long count() {
        return sensorRepository.count();
    }

    @Transactional
    public List<SensorEntity> findByFilter(Float ph, Integer waterTemperature, Integer ambientTemperature, Integer humidity, Float floatLevel) {
        return sensorRepository.findByFilter(ph, waterTemperature, ambientTemperature, humidity, floatLevel);
    }

    @Transactional
    public SensorEntity partialUpdate(Long id, SensorCreateDTO sensorCreateDTO) {
        SensorEntity sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sensor não encontrado"));

        if (sensorCreateDTO.getPh() != null) {
            sensor.setPh(sensorCreateDTO.getPh());
        }
        if (sensorCreateDTO.getWaterTemperature() != null) {
            sensor.setWaterTemperature(sensorCreateDTO.getWaterTemperature());
        }
        if (sensorCreateDTO.getAmbientTemperature() != null) {
            sensor.setAmbientTemperature(sensorCreateDTO.getAmbientTemperature());
        }
        if (sensorCreateDTO.getHumidity() != null) {
            sensor.setHumidity(sensorCreateDTO.getHumidity());
        }
        if (sensorCreateDTO.getFloatLevel() != null) {
            sensor.setFloatLevel(sensorCreateDTO.getFloatLevel());
        }

        sensor.setUpdatedAt(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));

        return sensorRepository.save(sensor);
    }
    
}
