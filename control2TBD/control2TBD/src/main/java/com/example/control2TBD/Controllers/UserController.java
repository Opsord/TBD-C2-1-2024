package com.example.control2TBD.Controllers;

import com.example.control2TBD.Entities.UserEntity;
import com.example.control2TBD.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    String homeLinkRedirect = "redirect:/user";

    @Autowired
    UserService userService;

/*
    @PostMapping("/register")
    public String registerUser(@RequestBody UserEntity newUser) {
        userService.newUser(newUser);
        return homeLinkRedirect;
    }

 */
    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity newUser) {
        userService.newUser(newUser);
        return ResponseEntity.ok(newUser);
    }


    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody UserEntity user) {
        String username = user.getUsername();
        String password = user.getPassword();

        UserEntity userFounded = userService.login(username, password);

        if (userFounded != null) {
            return ResponseEntity.ok(userFounded);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
