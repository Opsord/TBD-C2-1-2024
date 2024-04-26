package com.example.control2TBD.Repositories;

import com.example.control2TBD.Entities.TaskEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
//--------------------------CREATE--------------------------

    //insertTask
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO task_entity (title, description, expiredate, active, user_id) " +
            "VALUES (:title, :description, :expireDate, :isActive, :userId)", nativeQuery = true)
    void insertTask(@Param("title") String title,
                          @Param("description") String description,
                          @Param("expireDate") Date expireDate,
                          @Param("isActive") boolean isActive,
                          @Param("userId") Long userId);


//--------------------------UPDATE--------------------------

    //ChangeIs_activeById
    @Transactional
    @Modifying
    @Query(value = "UPDATE task_entity SET is_active = :completed WHERE id = :taskId", nativeQuery = true)
    void taskFinished(@Param("taskId") Long taskId, @Param("completed") boolean completed);

//---------------------------READ---------------------------

    //FindUserTaskByIduser
    @Query("SELECT t FROM TaskEntity t WHERE t.user.id = :userid")
    List<TaskEntity> findUserTasks(@Param("userid") String userid);


//--------------------------DELETE--------------------------

    //DeleteTaskById

    @Query("DELETE FROM TaskEntity WHERE id = :id")
    TaskEntity deleteTask(@Param("id") Long id);

}
