package com.example.BackendVolatile.vo.stakeholder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * lzj 2022.9.29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStateVO {

    private String deviceName;

    private Integer numOfUsers;   // 此设备的用户个数（参见个人信息中的device属性）

    private Integer numOfTasks;   // 可用此设备测试的任务的数量

    private Integer numOfRecruitingTasks;   // 可用此设备测试的正在招募的任务的数量
}
