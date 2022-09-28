package com.example.BackendVolatile.util.constant;

public enum RoleConstant {
    EMPLOYER(0),
    EMPLOYEE(1),
    ADMIN(2);

    private int role;

    public static final int ROLE_MAX = 2;
    public static final int ROLE_MIN = 0;

    RoleConstant(int role){
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public static int getRoleMax(){
        return ROLE_MAX;
    }

    public static int getRoleMin(){
        return ROLE_MIN;
    }
}
