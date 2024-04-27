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


    //state = Complete  => return lista de tareas completas
    //state = Incomplete => return lista de tareas incompletas
    @GetMapping("/{id}/{state}")
    public ResponseEntity<List<TaskEntity>> listComplete(@PathVariable String state, @PathVariable Long id){
        List<TaskEntity> listState = taskService.stateFilter(id,state);
        return ResponseEntity.ok(listState);

    }


    @GetMapping("/titleFilter/{id}/{title}")
    public ResponseEntity<List<TaskEntity>> taskByTitle(@PathVariable Long id, @PathVariable String title){
        List<TaskEntity> listTask = taskService.titleFilter(id, title);
        return ResponseEntity.ok(listTask);

    }


    @GetMapping("/keyWordFilter/{id}/{keyWord}")
    public ResponseEntity<List<TaskEntity>> listTaskByKeyWord(@PathVariable Long id, @PathVariable String keyWord){
        List<TaskEntity> listTask = taskService.keyWordFilter(id,keyWord);
        return ResponseEntity.ok(listTask);

    }

    

}