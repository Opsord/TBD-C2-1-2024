package com.example.control2TBD.Services;

import com.example.control2TBD.Entities.UserEntity;
import com.example.control2TBD.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity newUser(UserEntity user){
        String encryptedPassword = encryptPassword(user.getPassword());
        userRepository.insertUser(user.getName(),user.getSurname(),user.getUsername(),encryptedPassword);
        return user;
    }

    public String encryptPassword(String password){
        return passwordEncoder.encode(password);
    }

    public UserEntity login(String username, String password) {
        try {
            UserEntity userFound = userRepository.findUserByUsername(username);

            if(passwordEncoder.matches(password, userFound.getPassword())){
                return userFound;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
