package com.example.control2TBD.Services;

import com.example.control2TBD.Entities.UserEntity;
import com.example.control2TBD.Repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public UserEntity newUser(UserEntity user){
        userRepository.insertUser(user.getName(),user.getSurname(),user.getUsername(),user.getPassword());
        return user;
    }


}
