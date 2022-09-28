package com.example.BackendVolatile.util;

import com.example.BackendVolatile.mapper.user.UserMapper;
import com.example.BackendVolatile.util.constant.ResponseConstant;
import com.example.BackendVolatile.util.constant.RoleConstant;
import com.example.BackendVolatile.util.constant.VerificationMapConstant;
import com.example.BackendVolatile.vo.ResultVO;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class ParameterValidityVerification {

    @Resource
    UserMapper userMapper;

    public Map<String,Object> tokenVerification(int requestRole){
        long valid = -1;
        long userId = -1;
        ResultVO resultVO = new ResultVO();
        Map<String, Object> res = new HashMap<>();

        for(int i = 0 ;i < 1;i++){
            if(ThreadLocalUtils.getCache("valid") == null){
                resultVO = new ResultVO(ResponseConstant.TOKEN_FAILED);
                break;
            }
            if(ThreadLocalUtils.getCache("userId") == null){
                resultVO = new ResultVO(ResponseConstant.TOKEN_FAILED);
                break;
            }
            Long threadValid = ThreadLocalUtils.getCache("valid");

            if(threadValid==0){
                resultVO = new ResultVO(ResponseConstant.TOKEN_FAILED);
                break;
            }
            Long threadUserId = ThreadLocalUtils.getCache("userId");
            Integer role = userMapper.get_role_by_id(threadUserId);
            if(role != requestRole){
                resultVO = new ResultVO(ResponseConstant.REQUEST_UNCLEAR_ROLE);
                break;
            }

            valid = 1;
            userId = threadUserId;
        }

        res.put(VerificationMapConstant.VALID.getStr(), valid);
        res.put(VerificationMapConstant.USERID.getStr(), userId);
        res.put(VerificationMapConstant.RESULTVO.getStr(), resultVO);

        return res;

    }

    public Map<String,Object> tokenVerification(int[] requestRoleList){
        long valid = -1;
        long userId = -1;
        ResultVO resultVO = new ResultVO();
        Map<String, Object> res = new HashMap<>();

        for(int i = 0 ;i < 1;i++){
            if(ThreadLocalUtils.getCache("valid") == null){
                resultVO = new ResultVO(ResponseConstant.TOKEN_FAILED);
                break;
            }
            if(ThreadLocalUtils.getCache("userId") == null){
                resultVO = new ResultVO(ResponseConstant.TOKEN_FAILED);
                break;
            }
            Long threadValid = ThreadLocalUtils.getCache("valid");

            if(threadValid==0){
                resultVO = new ResultVO(ResponseConstant.TOKEN_FAILED);
                break;
            }
            Long threadUserId = ThreadLocalUtils.getCache("userId");
            Integer role = userMapper.get_role_by_id(threadUserId);
            boolean flag = false;
            for(int j = 0; j < requestRoleList.length;j++ ){
                if(role == requestRoleList[j]){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                resultVO = new ResultVO(ResponseConstant.REQUEST_UNCLEAR_ROLE);
                break;
            }

            valid = 1;
            userId = threadUserId;
        }

        res.put(VerificationMapConstant.VALID.getStr(), valid);
        res.put(VerificationMapConstant.USERID.getStr(), userId);
        res.put(VerificationMapConstant.RESULTVO.getStr(), resultVO);

        return res;

    }
}
