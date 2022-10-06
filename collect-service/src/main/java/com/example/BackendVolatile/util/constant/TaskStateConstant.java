package com.example.BackendVolatile.util.constant;

public enum TaskStateConstant {

    UNDERTAKING(0,"招募中"),

    FINISHED(1, "停止招募");

//    COMPLETED(2,"已完成：所有工人均已提交报告");


    private int code;
    private  String message;

    private static final int TASK_STATE_NUMBER = 2;


    private TaskStateConstant(int code, String message){
        this.code = code;
        this.message = message;
    }


    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

    public static int getTaskStateNumber(){
        return TASK_STATE_NUMBER;
    }

}
