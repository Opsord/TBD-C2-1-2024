package com.example.control2TBD.Repositories;

import com.example.control2TBD.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}