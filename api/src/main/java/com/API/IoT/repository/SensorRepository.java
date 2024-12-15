package com.API.IoT.repository;

import com.API.IoT.dto.sensor.SensorResponseDTO;
import com.API.IoT.entity.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Long>{
    @Query("SELECT s FROM SensorEntity s WHERE " +
    "(COALESCE(:ph, null) IS NULL OR s.ph = :ph) AND " +
    "(COALESCE(:waterTemperature, null) IS NULL OR s.waterTemperature = :waterTemperature) AND " +
    "(COALESCE(:ambientTemperature, null) IS NULL OR s.ambientTemperature = :ambientTemperature) AND " +
    "(COALESCE(:humidity, null) IS NULL OR s.humidity = :humidity) AND " +
    "(COALESCE(:floatLevel, null) IS NULL OR s.floatLevel = :floatLevel)")
    List<SensorEntity> findByFilter(
    @Param("ph") Float ph,
    @Param("waterTemperature") Integer waterTemperature,
    @Param("ambientTemperature") Integer ambientTemperature,
    @Param("humidity") Integer humidity,
    @Param("floatLevel") Float floatLevel);
}
