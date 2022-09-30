package com.example.BackendVolatile.vo.stakeholder;

import com.example.BackendVolatile.dao.stakeholder.EmployerDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * lzj 2022.9.29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerStateVO {

    private Long userId;

    //自定义的个人信息
    private String nickName;
    private String phoneNumber;

    private Integer publishedTaskNum;

    private Integer recruitingTaskNum;  // 发布过的任务中正在招募的任务个数

    // 发布过各种困难度的任务的次数，困难度可能为1，2，3，4，5
    private Map<Integer,Integer> numOfEachTaskDifficulty;

    private Map<String, Integer> numOfEachTaskType;  // 各类别任务的发布次数

    private Date lastTaskPublishedTime; // 上次发布任务的时间

    private Integer activeDegree;  // 百分制活跃度

    private Date lastLoginTime;

    public EmployerStateVO(EmployerDAO employerDAO,
                           Map<Integer,Integer> numOfEachTaskDifficulty,
                           Map<String,Integer> numOfEachTaskType,
                           Integer activeDegree){
        this.userId=employerDAO.getUserId();
        this.nickName=employerDAO.getNickName();
        this.phoneNumber=employerDAO.getPhoneNumber();
        this.publishedTaskNum=employerDAO.getPublishedTaskNum();
        this.recruitingTaskNum=employerDAO.getRecruitingTaskNum();
        this.lastLoginTime=employerDAO.getLastLoginTime()==null?null:new Date(employerDAO.getLastLoginTime());
        this.lastTaskPublishedTime=employerDAO.getLastTaskPublishedTime()==null?null:new Date(employerDAO.getLastTaskPublishedTime());

        this.numOfEachTaskDifficulty=numOfEachTaskDifficulty;
        this.numOfEachTaskType=numOfEachTaskType;
        this.activeDegree=activeDegree;
    }
}