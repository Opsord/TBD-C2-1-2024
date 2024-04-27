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


    //Change title
    @Transactional
    @Modifying
    @Query(value = "UPDATE task_entity SET title = :title WHERE id = :taskId", nativeQuery = true)
    void updateTitle(@Param("taskId") Long taskId, @Param("title") String title);


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



//--------------------------DELETE--------------------------

    //DeleteTaskById

    @Query("DELETE FROM TaskEntity WHERE id = :id")
    TaskEntity deleteTask(@Param("id") Long id);

}
