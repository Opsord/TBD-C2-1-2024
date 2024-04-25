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
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String description;
    private Date expiredate;
    private boolean status;
    @ManyToOne
    private UserEntity user;



}