package com.example.control2TBD.Repositories;

import com.example.control2TBD.Entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskEntityRepository extends JpaRepository<TaskEntity, Long> {
}