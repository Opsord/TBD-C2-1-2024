package com.example.control2TBD.Repositories;

import com.example.control2TBD.Entities.TaskEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO task_entity (title, description, expire_date, is_active, user_id) " +
            "VALUES (:title, :description, :expireDate, :isActive, :userId)", nativeQuery = true)
    void insertTask(@Param("title") String title, @Param("description") String description,
                    @Param("expireDate") Date expireDate, @Param("isActive") boolean isActive, @Param("userId") Long userId);

    @Query("SELECT t FROM TaskEntity t WHERE t.user.id = :userid")
    List<TaskEntity> findUserTasks(@Param("userid") String userid);



}