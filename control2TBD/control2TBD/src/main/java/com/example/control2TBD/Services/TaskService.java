package com.example.control2TBD.Services;

import com.example.control2TBD.Entities.TaskEntity;
import com.example.control2TBD.Entities.UserEntity;
import com.example.control2TBD.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public TaskEntity newTask(TaskEntity task){
        taskRepository.insertTask(task.getTitle(), task.getDescription(), task.getExpiredate(),task.isActive(), task.getUser().getId());
        System.out.println(task);
        return task;

    }
}
