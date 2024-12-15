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
@Table(name = "Sistema")
public class SystemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "ligar_luz", nullable = false)
    private Timestamp ligthOn;
    
    @Column(name = "deslifgar_luz", nullable = false)
    private Timestamp ligthOff;

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
