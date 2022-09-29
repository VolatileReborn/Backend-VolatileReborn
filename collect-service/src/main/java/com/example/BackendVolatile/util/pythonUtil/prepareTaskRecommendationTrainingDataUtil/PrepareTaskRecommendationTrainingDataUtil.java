package com.example.BackendVolatile.util.pythonUtil.prepareTaskRecommendationTrainingDataUtil;

import com.example.BackendVolatile.dao.UserDao.User;
import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.mapper.task.SelectTaskMapper;
import com.example.BackendVolatile.mapper.task.TaskMapper;
import com.example.BackendVolatile.mapper.user.LoginLogMapper;
import com.example.BackendVolatile.mapper.user.TaskFavorMapper;
import com.example.BackendVolatile.mapper.user.UserMapper;
import com.example.BackendVolatile.util.ActivityUtil;
import com.example.BackendVolatile.util.constant.RoleConstant;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PrepareTaskRecommendationTrainingDataUtil {
    @Resource
    UserMapper userMapper;

    @Resource
    TaskFavorMapper taskFavorMapper;

    @Resource
    TaskMapper taskMapper;

    @Resource
    SelectTaskMapper selectTaskMapper;

    @Resource
    ActivityUtil activityUtil;

    @Resource
    LoginLogMapper loginLogMapper;

    public PrepareTaskRecommendationTrainingDataDTO getPrepareTaskRecommendationTrainingDataDTO(){
        PrepareTaskRecommendationTrainingDataDTO pDTO = new PrepareTaskRecommendationTrainingDataDTO();
        List<Long> userIdList = userMapper.get_all_user_id_by_role(RoleConstant.EMPLOYEE.getRole());
        List<UserInfo> userInfos = new ArrayList<>();
        for(int i = 0; i<userIdList.size();i++){
            Long userId = userIdList.get(i);
            UserInfo userInfo = new UserInfo();
            userInfo.setUser_id(userId);
            UserFeature userFeature = new UserFeature();
            User user = userMapper.get_by_id(userId);
            List<String> deviceList = new ArrayList<>();
            System.out.println(user.toString());
            System.out.println("debug:"+user.getDevice());
            if(!(user.getDevice() == null)){
                deviceList = Arrays.asList(user.getDevice().split(", ").clone());
            }
            userFeature.setTest_devices(deviceList);
            userFeature.setProficiency(0.8);
            userFeature.setPreference(taskFavorMapper.get_task_favor_by_user_id(userId));
            //计算活跃度
            List<Long> login_time_stamp = loginLogMapper.get_login_time_list_by_user_id( userId);
            Integer activity = activityUtil.calActivity(login_time_stamp,10);
            userFeature.setActivity(1.0 * activity/100.0);
            userInfo.setUser_features(userFeature);
            //任务部分
            List<Long> taskList = selectTaskMapper.select_task_id_by_userid(userId);
            List<TaskInfo> relatedTask = new ArrayList<>();
            for(int j=0;j<taskList.size();j++){
                Long taskId = taskList.get(j);
                Task task = taskMapper.get_by_task_id(taskId);
                TaskFeature taskFeature = new TaskFeature();
                taskFeature.setTask_type(task.getTask_type()==0?"function test":"performance test");
                taskFeature.setTask_difficulty(task.getTask_difficulty()/5.0);
                taskFeature.setTask_introduction(task.getIntroduction());
                taskFeature.setWorker_num_left(task.getWorker_num_left());
                taskFeature.setWorker_num_total(task.getWorker_num_total());
                //设备
                List<String> device = new ArrayList<>();
                if(task.getLinux()){
                    device.add("Linux");
                }
                if(task.getIos()){
                    device.add("IOS");
                }
                if(task.getAndroid()){
                    device.add("Android");
                }
                taskFeature.setRequired_test_devices(device);
                TaskInfo taskInfo = new TaskInfo();
                taskInfo.setTask_id(taskId);
                taskInfo.setTask_features(taskFeature);
                relatedTask.add(taskInfo);
            }
            userInfo.setRelated_tasks(relatedTask);
            userInfos.add(userInfo);
        }
        pDTO.setUser_list(userInfos);
        return pDTO;
    }
}
