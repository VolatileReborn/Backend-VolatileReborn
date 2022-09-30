package com.example.BackendVolatile.dao.stakeholder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDAO {
    private Long userId;
    private String nickName;
    private String phoneNumber;
    private String device;
    private String professionalSkill;
    private Integer finishedTaskNum;
    private Integer acceptedTaskNum;
    private Integer collaborationNum;
    private Long lastLoginTime;
//    private List<String> taskFavorList;
//    private List<Integer> allScoreList;
//    private List<Integer> allDifficultyList;
//    private List<String> allTaskTypeList;
}
