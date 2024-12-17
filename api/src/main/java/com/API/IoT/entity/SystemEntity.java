package com.API.IoT.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.API.IoT.enums.SystemStatusEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Sistemas")
public class SystemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserEntity user;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SystemStatusEnum status;

    @Column(name = "ligar_luz", nullable = false)
    private LocalTime lightOn;
    
    @Column(name = "desligar_luz", nullable = false)
    private LocalTime lightOff;

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

    public SystemEntity(UserEntity user, SystemStatusEnum status, LocalTime lightOn, LocalTime lightOff) {
        this.user = user;
        this.status = status;
        this.lightOn = lightOn;
        this.lightOff = lightOff;
    }
}
