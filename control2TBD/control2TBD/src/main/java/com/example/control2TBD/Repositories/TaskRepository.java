package com.example.control2TBD.Repositories;

import com.example.control2TBD.Entities.TaskEntity;
import com.example.control2TBD.Entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
                          @Param("expireDate") Date expiredate,
                          @Param("isActive") boolean isActive,
                          @Param("userId") Long userId);


//--------------------------UPDATE--------------------------

    //ChangeIs_activeById
    @Transactional
    @Modifying
    @Query(value = "UPDATE task_entity SET is_active = :completed WHERE id = :taskId", nativeQuery = true)
    void taskFinished(@Param("taskId") Long taskId, @Param("completed") boolean completed);


    @Transactional
    @Modifying
    @Query(value = "UPDATE task_entity SET description = :description WHERE id = :taskId", nativeQuery = true)
    void updateDescription(@Param("taskId") Long taskId, @Param("description") String description);


    //Change title
    @Transactional
    @Modifying
    @Query(value = "UPDATE task_entity SET title = :title WHERE id = :taskId", nativeQuery = true)
    void updateTitle(@Param("taskId") Long taskId, @Param("title") String title);

    @Transactional
    @Modifying
    @Query(value = "UPDATE task_entity SET expiredate = :expiredate WHERE id = :taskId", nativeQuery = true)
    void updateDate(@Param("taskId") Long taskId, @Param("expiredate") Date expiredate);


//---------------------------READ---------------------------

    //FindUserTaskByIduser
    @Query("SELECT t FROM TaskEntity t WHERE t.user.id = :userid")
    List<TaskEntity> findUserTasks(@Param("userid") Long userid);


    //FindUserTaskByTitle
    @Query("SELECT t FROM TaskEntity t WHERE t.title = :title and t.user.id = :userid")
    List<TaskEntity> findUserTaskByTitle(@Param("userid") Long userid, @Param("title") String title);


    //FindTaskByKeyWord
    @Query("SELECT t FROM TaskEntity t WHERE t.description ILIKE %:keyword% AND t.user.id = :userid")
    List<TaskEntity> findUserTaskByKeyWord(@Param("userid") Long userid, @Param("keyword") String keyword);

    @Query("SELECT t FROM TaskEntity t WHERE t.id = :id")
    TaskEntity findTaskById(@Param("id") Long id);




//--------------------------DELETE--------------------------

    //DeleteTaskById

    @Query("DELETE FROM TaskEntity WHERE id = :id")
    TaskEntity deleteTask(@Param("id") Long id);

}
