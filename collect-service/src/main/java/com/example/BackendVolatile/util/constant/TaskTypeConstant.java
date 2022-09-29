package com.example.BackendVolatile.util.constant;

import com.example.BackendVolatile.vo.taskVO.PublishTaskVO;

public enum TaskTypeConstant {

    FUNCTIONAL_TEST(0,"功能测试"),

    PERFORMANCE_TEST(1,"性能测试");


    private int code;
    private String message;

    public static final int TASK_TYPE_MAX = 1;

    public static final int TASK_TYPE_MIN = 0;

    private TaskTypeConstant(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage(){
        return message;
    }
}
