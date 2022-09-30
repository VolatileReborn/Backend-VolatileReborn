package com.example.BackendVolatile.dao.stakeholder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDAO {
    private Long userId;
    private String nickName;
    private String phoneNumber;
    private Integer publishedTaskNum;
    private Integer recruitingTaskNum;
    private Long lastTaskPublishedTime;
    private Long lastLoginTime;
}
