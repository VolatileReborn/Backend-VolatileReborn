package com.example.BackendVolatile.util.pythonUtil.getRecommendedTaskUtil;

import com.example.BackendVolatile.dao.UserDao.User;
import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.mapper.report.RecommendationRuleMapper;
import com.example.BackendVolatile.mapper.task.SelectTaskMapper;
import com.example.BackendVolatile.mapper.task.TaskMapper;
import com.example.BackendVolatile.mapper.user.LoginLogMapper;
import com.example.BackendVolatile.mapper.user.TaskFavorMapper;
import com.example.BackendVolatile.mapper.user.UserMapper;
import com.example.BackendVolatile.util.ActivityUtil;
import com.example.BackendVolatile.util.constant.RecommendationRuleClassificationConstant;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetRecommendedTaskUtil {
    @Resource
    RecommendationRuleMapper recommendationRuleMapper;

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

    public GetRecommendedTaskDTO getRecommendedTask(Long userId){
        GetRecommendedTaskDTO getRecommendedTaskDTO = new GetRecommendedTaskDTO();
        List<String> et = recommendationRuleMapper.get_feature_by_classification(
                RecommendationRuleClassificationConstant.EMPHASIZED_TASK_FEATURES);
        List<String> eu = recommendationRuleMapper.get_feature_by_classification(
                RecommendationRuleClassificationConstant.EMPHASIZED_USER_FEATURES);
        List<String> dt = recommendationRuleMapper.get_feature_by_classification(
                RecommendationRuleClassificationConstant.DESALTED_TASK_FEATURES);
        List<String> du = recommendationRuleMapper.get_feature_by_classification(
                RecommendationRuleClassificationConstant.DESALTED_USER_FEATURES);

        List<Long> taskIdList = selectTaskMapper.select_task_id_by_userid( userId);
        List<Task> taskList = new ArrayList<>();
        for(int j = 0; j <taskIdList.size();j++){
            taskList.add(taskMapper.get_by_task_id(taskIdList.get(j)));
        }
        List<String> taskFavorList = taskFavorMapper.get_task_favor_by_user_id( userId);
        List<Long> login_time_stamp = loginLogMapper.get_login_time_list_by_user_id( userId);
        Integer activity = activityUtil.calActivity(login_time_stamp,10);
        User user = userMapper.get_by_id(userId);
        getRecommendedTaskDTO.setUser(new UserInfo(user,taskList,activity,taskFavorList));

        getRecommendedTaskDTO.setRecommendation_threshold(0.1);

        getRecommendedTaskDTO.setAlgorithm("ItemCF");
        getRecommendedTaskDTO.setRecommended_task_num(10);
        getRecommendedTaskDTO.setRecommendation_rules(new Rule(eu,et,du,dt));
        return getRecommendedTaskDTO;

    }

    public List<Long> parse(String s){
        System.out.println("s11s-s--------");
        System.out.println(s);
        String[] stringList = s.split("\"task_id\": ");
        List<Long> res = new ArrayList<>();
        if(stringList.length == 1){
            return res;
        }
        else{
            for(int i = 1;i<stringList.length;i++){
                Long temp = Long.parseLong(stringList[i].split(", ")[0]);
                res.add(temp);
            }
        }
        System.out.println(res);
        return res;
    }



}
