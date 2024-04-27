package com.example.control2TBD.Services;

import com.example.control2TBD.Entities.TaskEntity;
import com.example.control2TBD.Entities.UserEntity;
import com.example.control2TBD.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    //List of task by state
    public ArrayList<TaskEntity> stateFilter(Long userid, String state) {
        List<TaskEntity> allTask = taskRepository.findUserTasks(userid);
        ArrayList<TaskEntity> stateTask =  new ArrayList<>();
        if (state.equals("Complete")) {
            for (int i = 0; i < allTask.size(); i++) {
                if (allTask.get(i).isActive()) {
                    stateTask.add(allTask.get(i));
                }
            }
            return stateTask;
        } else if (state.equals("Incomplete")) {
            for (int i = 0; i < allTask.size(); i++) {
                if (!allTask.get(i).isActive()) {
                    stateTask.add(allTask.get(i));
                }
            }

        }
        return (ArrayList<TaskEntity>) allTask;
    }



    //Filter by title
    public List<TaskEntity> titleFilter(Long userid, String title) {
        List<TaskEntity> allTask = taskRepository.findUserTasks(userid);
        List<TaskEntity> stateTask =  new ArrayList<>();
        for(TaskEntity task : allTask){
            if(task.getTitle().equals(title)){
                stateTask.add(task);
            }
        }
        return stateTask;
    }



    //Filer by key word
    public List<TaskEntity> keyWordFilter(Long userid, String keyWord) {
        List<TaskEntity> keyWordTask = taskRepository.findUserTaskByKeyWord(userid, keyWord);
        return keyWordTask;
    }




}
