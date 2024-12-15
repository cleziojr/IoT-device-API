package com.API.IoT.repository;

import com.API.IoT.dto.user.SensorResponseDTO;
import com.API.IoT.entity.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SensorRepository extends JpaRepository<SensorEntity, Long>{}
