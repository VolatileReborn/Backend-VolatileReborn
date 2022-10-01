package com.example.BackendVolatile.util.constant;

public enum ReportStateConstant {

    TO_BE_REVIEWED(0,"待审核"),

    REVIEWED(1, "已审核"),

    TO_BE_COOPERATED(2,"待协作"),

    COOPERATED(3,"已协作");

    private int code;
    private  String message;

    private static final int REPORT_STATE_NUMBER = 2;


    private ReportStateConstant(int code, String message){
        this.code = code;
        this.message = message;
    }


    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

    public static int getReportStateNumber() {
        return REPORT_STATE_NUMBER;
    }

    //    private static int TO_BE_REVIEWED = 0;


}
