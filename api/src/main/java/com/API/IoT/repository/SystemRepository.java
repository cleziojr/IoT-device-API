package com.API.IoT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.API.IoT.entity.SystemEntity;

import java.util.Optional;

@Repository
public interface SystemRepository extends JpaRepository<SystemEntity, Long>  {
    Optional<SystemEntity> findByUserId(String user_id);
}
