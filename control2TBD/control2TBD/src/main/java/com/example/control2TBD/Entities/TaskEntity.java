package com.example.control2TBD.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "task_entity")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String description;
    private Date expiredate;
    private boolean active;

    // Relación many-to-one con UserEntity
    @ManyToOne
    @JoinColumn(name = "user_id") // Nombre de la columna que actúa como clave externa
    private UserEntity user;

}
