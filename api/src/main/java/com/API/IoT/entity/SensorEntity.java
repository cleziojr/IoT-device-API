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
@Table(name = "Sensores")
public class SensorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sis_id")
    private Long id;

    @Column(name = "ph", nullable = false)
    private float ph;

    @Column(name = "temperatura_agua", nullable = false)
    private int waterTemperature;

    @Column(name = "temperatura_ambiente", nullable = false)
    private int ambientTemperature;

    @Column(name = "umidade", nullable = false)
    private int humidity;

    @Column(name = "nivel_agua", nullable = false)
    private float floatLevel;

    @Column(name = "criado_em", nullable = false)
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
