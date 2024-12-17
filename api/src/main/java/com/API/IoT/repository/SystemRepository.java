package com.API.IoT.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.API.IoT.entity.SystemEntity;

@Repository
public interface SystemRepository extends JpaRepository<SystemEntity, Long>  {
    List<SystemEntity> findByUserId(Long userId);
}
