package com.API.IoT.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Sensor")
public class SensorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sis_id")
    private Long id;

    @Column(name = "ph", nullable = false)
    private float ph;

    @Column(name = "temperatura_agua", nullable = false)
    private int water_temperature;

    @Column(name = "temperatura_ambiente", nullable = false)
    private int ambient_temperature;

    @Column(name = "umidade", nullable = false)
    private int humidity;

    @Column(name = "nivel_agua", nullable = false)
    private float float_level;

    @Column(name = "criado_em", nullable = false)
    private Timestamp createdAt;

    @Column(name = "atualizado_em", nullable = false)
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
