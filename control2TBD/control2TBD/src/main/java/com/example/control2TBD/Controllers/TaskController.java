package com.example.control2TBD.Controllers;

import com.example.control2TBD.Entities.TaskEntity;
import com.example.control2TBD.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @PostMapping("/delete")
    public ResponseEntity<TaskEntity> deleteTask(@RequestBody TaskEntity task){
        return ResponseEntity.ok(taskService.deleteTask(task));

    }

    @PostMapping("/{id}/markFinished")
    public ResponseEntity<TaskEntity> markFinished(@RequestBody TaskEntity task){
        return ResponseEntity.ok(taskService.markFinished(task));

    }

    @PostMapping("/{id}/markUnfinished")
    public ResponseEntity<TaskEntity> markUnfinished(@RequestBody TaskEntity task){
        return ResponseEntity.ok(taskService.markUnfinished(task));

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


    @GetMapping("/taskByUser/{id}")
    public ResponseEntity<List<TaskEntity>> taskByUser(@PathVariable Long id){
        List<TaskEntity> listTask = taskService.getTaskByIdUser(id);
        return ResponseEntity.ok(listTask);

    }


    //http://localhost:8090/task/UpdateTitle/2
    @PutMapping("/UpdateTitle/{taskId}")
    public ResponseEntity<String> updateTaskTitle(@PathVariable Long taskId, @RequestBody String newTitle) {
        taskService.updateTaskTitle(taskId, newTitle);
        return new ResponseEntity<>("Título de tarea actualizado correctamente", HttpStatus.OK);
    }

    @PutMapping("/UpdateDes/{taskId}")
    public ResponseEntity<String> updateTaskDescription(@PathVariable Long taskId, @RequestBody String newDes) {
        taskService.updateTaskDescription(taskId, newDes);
        return new ResponseEntity<>("Descripcion de tarea actualizado correctamente", HttpStatus.OK);
    }

    @PutMapping("/UpdateDate/{taskId}")
    public ResponseEntity<String> updateTaskDate(@PathVariable Long taskId, @RequestBody Date newDate) {
        taskService.updateTaskDate(taskId, newDate);
        return new ResponseEntity<>("Fecha de expiracion de tarea actualizado correctamente", HttpStatus.OK);
    }



}