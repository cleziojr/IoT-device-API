package com.API.IoT.repository;

import java.util.List;
import com.API.IoT.entity.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Long> {
    List<SensorEntity> findBySystemId(Long systemId); 
}
