package com.example.control2TBD.Controllers;

import com.example.control2TBD.Entities.TaskEntity;
import com.example.control2TBD.Entities.UserEntity;
import com.example.control2TBD.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    TaskService taskService;
    @PostMapping("/create")
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task) {
        taskService.newTask(task);
        return ResponseEntity.ok(task);
    }
}