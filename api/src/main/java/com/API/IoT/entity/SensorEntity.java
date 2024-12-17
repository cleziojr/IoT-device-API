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
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sistema", nullable = false)
    private SystemEntity system;

    @Column(name = "ph", nullable = false)
    private float ph;

    @Column(name = "temperatura_agua", nullable = false)
    private int waterTemperature;

    @Column(name = "temperatura_ambiente", nullable = false)
    private int ambientTemperature;

    @Column(name = "umidade", nullable = false)
    private int humidity;

    @Column(name = "nivel_agua", nullable = false)
    private float waterLevel;

    @Column(name = "criado_em", nullable = false)
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public SensorEntity(SystemEntity systemEntity, float ph, int waterTemperature, int ambientTemperature, int humidity, float waterLevel) {
        this.system = systemEntity;
        this.ph = ph;
        this.waterTemperature = waterTemperature;
        this.ambientTemperature = ambientTemperature;
        this.humidity = humidity;
        this.waterLevel = waterLevel;
    }
}
