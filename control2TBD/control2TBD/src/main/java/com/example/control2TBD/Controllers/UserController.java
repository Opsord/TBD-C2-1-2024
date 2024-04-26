package com.example.control2TBD.Controllers;

import com.example.control2TBD.Entities.UserEntity;
import com.example.control2TBD.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    String homeLinkRedirect = "redirect:/users";

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public String registerUser(@RequestBody UserEntity newUser){
        userService.newUser(newUser);
        return homeLinkRedirect;
    }
}
