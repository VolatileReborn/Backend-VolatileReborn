package com.example.BackendVolatile.util.constant;

public enum CooperationReportStateConstant {
    COOPERATING("协作报告未提交", 0),
    COOPERATED("协作报告已提交", 1);

    private String state;

    private int code;

    CooperationReportStateConstant(String state, int code){
        this.code = code;
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public String getState() {
        return state;
    }
}
