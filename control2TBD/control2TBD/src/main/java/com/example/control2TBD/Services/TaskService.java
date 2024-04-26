package com.example.control2TBD.Services;

import com.example.control2TBD.Entities.TaskEntity;
import com.example.control2TBD.Entities.UserEntity;
import com.example.control2TBD.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public TaskEntity newTask(TaskEntity task){
        taskRepository.insertTask(task.getTitle(), task.getDescription(), task.getExpiredate(),task.isActive(), task.getUser().getId());
        System.out.println(task);
        return task;

    }


    //lista de activos
    public List<TaskEntity> statefilter(Long userid, String state) {
        List<TaskEntity> allTask = taskRepository.findUserTasks(userid);
        List<TaskEntity> stateTask = null;
        if (state.equals("Complet")) {
            for (int i = 0; i < allTask.size(); i++) {
                if (allTask.get(i).isActive()) {
                    stateTask.add(allTask.get(i));
                }
            }
        } else {
            for (int i = 0; i < allTask.size(); i++) {
                if (!allTask.get(i).isActive()) {
                    stateTask.add(allTask.get(i));
                }
            }
        }
        return stateTask;
    }
}
