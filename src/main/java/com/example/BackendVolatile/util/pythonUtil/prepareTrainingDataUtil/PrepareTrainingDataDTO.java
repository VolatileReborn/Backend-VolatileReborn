package com.example.BackendVolatile.util.pythonUtil.prepareTrainingDataUtil;

import com.example.BackendVolatile.dao.UserDao.User;
import com.example.BackendVolatile.dao.taskDAO.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class PrepareTrainingDataDTO {
    List<UserInfo> user_list;

    public PrepareTrainingDataDTO(){
        this.user_list = new ArrayList<>();
    }

}

@Data
@NoArgsConstructor
class UserInfo{
    public Long user_id;
    public Feature user_features;
    public List<TaskInfo> related_tasks;

    public UserInfo(User user, List<Task> taskList, int activity, List<String> taskFavor){
        this.related_tasks = new ArrayList<>();
        this.user_features = new Feature();
        this.user_id = user.getUser_id();
        this.user_features.proficiency = 0.5;
        this.user_features.activity = activity / 100.0;
        if(taskFavor.size()==0){
            this.user_features.preference = new ArrayList<>();
        }
        else{
            for(int i=0;i<Math.max(taskFavor.size(),2);i++){
                this.user_features.preference.add(taskFavor.get(i));
            }
        }
        if(user.getDevice() == null){
            System.out.println("null");
        }
        if(user.getDevice() == ""){
            System.out.println("0");
        }
        this.user_features.test_devices.add(user.getDevice() == null ? "" : user.getDevice());

        for(int i=0;i<taskList.size();i++){
            this.related_tasks.add(new TaskInfo(taskList.get(i)));
        }

    }


}

@Data
class Feature{
    public Double proficiency;
    public List<String> preference;
    public Double activity;
    public List<String> test_devices;

    public Feature(){
        this.preference = new ArrayList<>();
        this.test_devices = new ArrayList<>();
    }

}

@Data
class TaskInfo{
    public Long task_id;
    public TaskFeature task_features;

    public TaskInfo(){
        this.task_features = new TaskFeature();
    }

    public TaskInfo(Task task){
        this.task_id = task.getTask_id();
        this.task_features = new TaskFeature();
        this.task_features.task_type =  task.getTask_type() == 0 ? "function test" : "performance test";
        this.task_features.task_introduction =task.getIntroduction();
        this.task_features.task_difficulty = task.getTask_difficulty()/5.0;
        this.task_features.worker_num_total = task.getWorker_num_total();
        this.task_features.worker_num_left = task.getWorker_num_left();
        List<String> device = new ArrayList<>();
        if(task.getIos()){
            device.add("ios");
        }
        if(task.getLinux()){
            device.add("linux");
        }
        if(task.getAndroid()){
            device.add("android");
        }
        if(device.size()==0){
            device.add("test");
        }
        this.task_features.required_test_devices = device;

    }
}

@Data
class TaskFeature{
    public String task_type;
    public String task_introduction;
    public Double task_difficulty;
    public Integer worker_num_total;
    public Integer worker_num_left;
    public List<String> required_test_devices;

    public TaskFeature(){
        this.required_test_devices = new ArrayList<>();
    }
}