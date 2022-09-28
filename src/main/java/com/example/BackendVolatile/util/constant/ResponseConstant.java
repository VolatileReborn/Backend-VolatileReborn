package com.example.BackendVolatile.util.constant;

public enum ResponseConstant {

    TOKEN_FAILED(1, "登录状态无效，请重新登陆"),  //token失效

    FAILED(2, "请求参数格式错误"),

    GET_DATA_FAILED(3, "获取数据失败"),


    REGISTRATION_SUCCEEDED(100," 注册成功"),

    REGISTRATION_FAILED(101,"注册失败"),

    REGISTRATION_MISSING_INFORMATION(102,"缺少必填信息，注册失败"),

    REGISTRATION_REPEATED(103, "该账号已注册,请直接登陆"),

    REGISTRATION_WRONG_PHONE_NUMBER_FORMAT(103,"手机号格式错误，请重新填写"),

    REGISTRATION_WRONG_ROLE_FORMAT(104,"用户角色数据错误，请重新填写"),

    REGISTRATION_MISSING_NICKNAME(105,"昵称不得为空，注册失败"),

    REGISTRATION_MISSING_PASSWORD(106,"密码不得为空，注册失败"),




    LOGIN_SUCCESS(200,"登陆成功"),

    LOGIN_FAILED(201,"账号或密码错误，登录失败"),

    LOGIN_NOT_YET_REGISTERED(202, "账号未注册，请注册后登录"),

    LOGIN_WRONG_PHONE_NUMBER_FORMAT(203,"手机号格式错误，请重新填写"),



    REQUEST_DATA_SUCCEEDED(300,"请求或获取数据成功"),

    REQUEST_PUBLISH_TASK_SUCCEEDED(300,"任务发布成功"),

    REQUEST_ACCEPT_TASK_SUCCEEDED(300,"任务接受成功"),

    REQUEST_MISSING_INFORMATION(301,"缺少必要信息或格式错误，请求或获取数据失败"),

    REQUEST_DATA_FAILED(302,"没有发布过任务"),

    REQUEST_NO_USER(303,"用户不存在，请求或获取数据失败"),

    REQUEST_NO_TASK(304,"任务不存在，请求或获取数据失败"),

    REQUEST_NO_REPORT(305,"报告不存在，请求或获取数据失败"),

    REQUEST_NO_DATA(306,"请求数据为空，请求失败"),

    REQUEST_GET_DATA_FAILED(307,"从数据库获取数据失败"),

    REQUEST_GET_ROLE_FAILED(308,"获取用户身份失败"),

    REQUEST_NO_ACCESS_TO_PUBLISH_TASK(309,"没有发布任务的权限"),

    REQUEST_UNCLEAR_ROLE(310,"用户身份异常，请重新登陆"),

    REQUEST_NO_ACCESS_TO_ACCEPT_TASK(311,"没有接受任务的权限"),

    REQUEST_ACCEPT_TASK_REPEATED(312,"你已经参与了这个任务"),




    EMPLOYEE_SUCCEEDED(400,"请求数据成功"),

    EMPLOYEE_SET_USER_PROFILE_SUCCEEDED(400,"设置用户资料成功"),

    EMPLOYEE_SCORE_THE_REPORT_SUCCEEDED(400,"评分成功"),


    EMPLOYEE_UPLOAD_REPORT_SUCCEEDED(400,"上传报告成功"),

    EMPLOYEE_NO_AUTHORITY_TO_DO(401,"没有权限进行此操作"),

    EMPLOYEE_NO_UNDERTAKING_TASKS(400,"该用户没有正在进行的任务"),

    EMPLOYEE_NOT_SELECT_THE_TASK(402,"您没有接受这个任务，无法查看详情"),

    EMPLOYEE_UNCLEAR_SELECTED_STATE(403,"判断任务选择状态时发生异常，请联系管理员"),

    EMPLOYEE_UNKNOWN_ERROR_WHEN_PROCESS(404,"处理数据中出现异常"),

    EMPLOYEE_NOT_MATCH_THE_TASK(505,"报告与任务不对应，请求数据失败"),

    EMPLOYEE_NOT_MATCH_THE_USER(506,"这不是你的报告，请求数据失败"),

    EMPLOYEE_REPORT_HAVE_SUBMITTED(507,"该任务您已提交过报告，无法再次提交"),

    EMPLOYEE_UNCLEAR_SUBMIT_STATE(508,"报告提交状态异常，请联系管理员"),

    EMPLOYEE_NOT_SELECT_THE_TASK_AND_SUBMIT_REPORT(402,"您没有接受这个任务，无法提交报告"),

    EMPLOYEE_CANNOT_MODIFY_REPORT(403,"提交近期您无法修改报告,请在规定时间后修改"),



    EMPLOYER_SUCCEEDED(500,"请求数据成功"),

    EMPLOYER_UPDATE_TASK_STATE_SUCCEEDED(500,"更新任务状态成功"),

    EMPLOYER_NO_AUTHORITY_TO_DO(501,"没有权限进行此操作"),

    EMPLOYER_NOT_YOUR_TASK(502,"该任务不是你发布的任务，无法查看任务详情"),

    EMPLOYER_TASK_ALREADY_FINISHED(503,"该任务已经停止招募"),

    EMPLOYER_UNCLEAR_TASK_STATE(504,"任务状态异常，请联系管理员"),

    EMPLOYER_NOT_YOUR_REPORT(505,"不是你发布的任务的报告，无法查看任务详情"),

    EMPLOYER_NOT_REPORT_FOR_THE_TASK(506,"报告与任务不对应，请求失败"),

    EMPLOYER_NO_UNDERTAKING_TASKS(500,"该用户没有正在进行的任务"),





    TASK_FULL(601,"参与人数已满，无法继续"),

    TASK_UNCLEAR_WORKER(602,"任务人数异常，请联系管理员"),

    ADMIN_SET_RECOMMENDATION_RULE_SUCCESS(700, "设置任务推荐规则成功"),

    ADMIN_SET_RULE_SUCCESS(700, "设置相似度计算算法成功"),





    NULL(999,"wu");




    private int code;

    private String message;

    private ResponseConstant(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
