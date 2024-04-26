package com.example.control2TBD.Controllers;

import com.example.control2TBD.Entities.TaskEntity;
import com.example.control2TBD.Entities.UserEntity;
import com.example.control2TBD.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}/{state}")
    public ResponseEntity<List<TaskEntity>> listComplete(@PathVariable String state, @PathVariable Long id){
        List<TaskEntity> listState = taskService.statefilter(id,state);
        return ResponseEntity.ok(listState);

    }
}