package com.example.control2TBD.Repositories;

import com.example.control2TBD.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}