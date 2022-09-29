package com.example.BackendVolatile.util.pythonUtil.prepareTaskRecommendationTrainingDataUtil;

import lombok.Data;

import java.util.List;

@Data
public class PrepareTaskRecommendationTrainingDataDTO {
    List<UserInfo> user_list;
}

@Data
class UserInfo {
    Long user_id;
    UserFeature user_features;
    List<TaskInfo> related_tasks;

    public void setUser_features(UserFeature user_features) {
        this.user_features = user_features;
    }
}

@Data
class UserFeature{
    Double proficiency;
    List<String> preference; //多选，最多两个元素
    Double activity;
    List<String> test_devices;

}

@Data
class TaskInfo {
    Long task_id;
    TaskFeature task_features;


    public void setTask_features(TaskFeature task_features) {
        this.task_features = task_features;
    }
}

@Data
class TaskFeature{
    String task_type;
    String task_introduction;
    Double task_difficulty;
    Integer worker_num_total;
    Integer worker_num_left;
    List<String> required_test_devices;

}
