package com.example.BackendVolatile.vo.employerVO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CompositeTaskStateVO {
    private Long id;  // 复合任务id
    private String taskName;  // 复合任务名称
    private String taskIntroduction;  // 复合任务简介
    private Date publishTime;  // 复合任务发布时间

    /**
     * 复合任务状态
     *
     * @see CompositeTaskState
     */
    private CompositeTaskState state;

    /**
     * 子任务列表
     *
     * @see InnerSubTaskVO
     */
    private List<InnerSubTaskVO> subTasks;

    /**
     * 子任务的时序关系，即前后子任务索引对的集合
     * 例如子任务列表subtasks={A,B,C,D}，A/B/C/D在列表中的索引为0/1/2/3
     * 且时序关系为{A在B前，A在C前，B在D前，C在D前}
     * 则timingRel={(0,1),(0,2),(1,3),(2,3)}
     *
     * @see TaskOrderPairVO
     */
    private List<TaskOrderPairVO> timingRel;

    @Data
    @NoArgsConstructor
    static class InnerSubTaskVO {
        private Long taskId;  // 子任务id，"复合任务的子任务"相当于"普通任务"
        private String taskName;  // 子任务名称

        /**
         * 子任务类型
         *
         * @see com.example.BackendVolatile.util.constant.TaskTypeConstant
         */
        private Integer taskType;

        private SubTaskState taskState;  // 子任务状态
        private Integer workerNumTotal;  // 所需工人数
        private Integer workerNumLeft;  // 剩余名额
    }

    @Data
    @NoArgsConstructor
    static class TaskOrderPairVO {
        private Integer preTaskIndex; // 后序子任务在子任务列表中的索引
        private Integer postTaskIndex; // 后序子任务在子任务列表中的索引
    }

    // 整个复合任务的状态
    enum CompositeTaskState {
        IN_PROGRESS, // 正在进行中
        COMPLETED  // 所有子任务状态为COMPLETED
    }

    // 子任务状态
    enum SubTaskState {
        NOT_STARTED, // 有先序任务非COMPLETED，未开始招募
        RECRUITING, // 所有先序任务COMPLETED，已经开始招募，但报名人数未满
        IN_PROGRESS, // 报名人数已满但还有工人未提交报告
        COMPLETED // 所有工人已提交报告
    }
}
