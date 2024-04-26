package com.example.control2TBD.Controllers;

import com.example.control2TBD.Auth.JwtUtil;
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


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity newUser) {
        System.out.println(newUser);
        UserEntity registeredUser = userService.newUser(newUser);
        if (registeredUser != null) {
            // Generate JWT token if registration is successful
            String token = JwtUtil.generateToken(registeredUser.getUsername());
            // Include token in the response
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token) // Token in header
                    .body(registeredUser);
        } else {
            return ResponseEntity.badRequest().body("Registration failed");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity user) {
        String username = user.getUsername();
        String password = user.getPassword();
        UserEntity userFound = userService.login(username, password);

        if (userFound != null) {
            // Generate JWT token
            String token = JwtUtil.generateToken(username);
            // Include token in the response header or body
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token) // Token in header
                    .body(userFound);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}





