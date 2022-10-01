package com.example.BackendVolatile.vo.stakeholder;

import com.example.BackendVolatile.dao.stakeholder.EmployeeDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * lzj 2022.9.29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeStateVO {

    private Long userId;

    // 自定义的个人信息
    private String nickName;
    private String phoneNumber;
    private List<String> taskFavorList;
    private String device;              // 常用测试设备
    private String professionalSkill;

    private Integer finishedTaskNum;    // 即提交过报告（非协作报告）的次数

    private Integer acceptedTaskNum;

    private Integer collaborationNum;   // 参与过的协作次数

    // 获得过的各种报告评分的次数，评分可能为1，2，3，4，5
    private Map<Integer,Integer> numOfEachScore;

    // 接受过各种困难度的任务的次数，困难度可能为1，2，3，4，5
    private Map<Integer,Integer> numOfEachTaskDifficulty;

    private Map<String,Integer> numOfEachTaskType;   // 参与过的各类别的任务的次数

    private Integer activeDegree;   // 百分制活跃度

    private Date lastLoginTime;

    public EmployeeStateVO(@NonNull EmployeeDAO employeeDAO,
                           List<String> taskFavorList,
                           Map<Integer,Integer> numOfEachScore,
                           Map<Integer,Integer> numOfEachTaskDifficulty,
                           Map<String,Integer> numOfEachTaskType,
                           Integer activeDegree){
        this.userId= employeeDAO.getUserId();
        this.nickName= employeeDAO.getNickName();
        this.phoneNumber= employeeDAO.getPhoneNumber();
        this.device= employeeDAO.getDevice();
        this.professionalSkill= employeeDAO.getProfessionalSkill();
        this.finishedTaskNum= employeeDAO.getFinishedTaskNum();
        this.acceptedTaskNum= employeeDAO.getAcceptedTaskNum();
        this.collaborationNum= employeeDAO.getCollaborationNum();
        this.lastLoginTime= employeeDAO.getLastLoginTime()==null?null:new Date(employeeDAO.getLastLoginTime());

        this.taskFavorList=taskFavorList;
        this.numOfEachScore=numOfEachScore;
        this.numOfEachTaskDifficulty=numOfEachTaskDifficulty;
        this.numOfEachTaskType=numOfEachTaskType;
        this.activeDegree=activeDegree;
    }
}
