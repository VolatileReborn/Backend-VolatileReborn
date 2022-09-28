package com.example.BackendVolatile.util.constant;

public enum VerificationMapConstant {
    RESULTVO("resultVO",0),

    VALID("valid",1),

    USERID("userId", 2);


    private String str;
    private int code;
    VerificationMapConstant(String str, int code){
        this.code = code;
        this.str = str;
    }

    public String getStr(){
        return this.str;
    }

    public int getCode(){
        return this.code;
    }
}
