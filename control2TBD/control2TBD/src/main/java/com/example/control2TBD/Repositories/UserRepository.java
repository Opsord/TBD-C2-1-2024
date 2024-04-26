package com.example.control2TBD.Repositories;

import com.example.control2TBD.Entities.TaskEntity;
import com.example.control2TBD.Entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_entity (name, surname, username, password)" +
            "VALUES (:name, :surname, :username,segun yo :password)", nativeQuery = true)
    UserEntity insertUser(@Param("name") String name, @Param("surname") String surname,
                          @Param("username") String username, @Param("password") String password);


    @Transactional
    @Modifying
    @Query(value = "UPDATE user_entity SET password = :password WHERE id = :id", nativeQuery = true)
    void passwordChange(@Param("id") Long id, @Param("password") String password);

        //findByUsername
    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    UserEntity findUserByUsername(@Param("username") String username);

    //findById
    @Query("SELECT u FROM UserEntity u WHERE u.username = :id")
    UserEntity findUserById(@Param("id") Long id);

    //deleteById
    @Query("DELETE FROM UserEntity WHERE id = :id")
    UserEntity deleteUser(@Param("id") Long id);

}

