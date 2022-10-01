package com.example.BackendVolatile.util.pythonUtil.prepareTrainingDataUtil;

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
import java.util.List;

@Component
public class PrepareTrainingDataUtil {

    @Resource
    UserMapper userMapper;

    @Resource
    TaskMapper taskMapper;

    @Resource
    SelectTaskMapper selectTaskMapper;

    @Resource
    TaskFavorMapper taskFavorMapper;

    @Resource
    ActivityUtil activityUtil;

    @Resource
    LoginLogMapper loginLogMapper;

    public PrepareTrainingDataDTO getPrepareTrainingDataDTO(){
        PrepareTrainingDataDTO prepareTrainingDataDTO = new PrepareTrainingDataDTO();

        List<User> userList = userMapper.get_by_role(RoleConstant.EMPLOYEE.getRole());
        for(int i=0;i<userList.size();i++){
            List<Long> taskIdList = selectTaskMapper.select_task_id_by_userid(userList.get(i).getUser_id());
            List<Task> taskList = new ArrayList<>();
            for(int j = 0; j <taskIdList.size();j++){
                taskList.add(taskMapper.get_by_task_id(taskIdList.get(j)));
            }
            List<String> taskFavorList = taskFavorMapper.get_task_favor_by_user_id(userList.get(i).getUser_id());
            List<Long> login_time_stamp = loginLogMapper.get_login_time_list_by_user_id(userList.get(i).getUser_id());
            Integer activity = activityUtil.calActivity(login_time_stamp,10);
            prepareTrainingDataDTO.user_list.add(new UserInfo(userList.get(i),taskList,activity,taskFavorList));
        }

        return prepareTrainingDataDTO;
    }
}
