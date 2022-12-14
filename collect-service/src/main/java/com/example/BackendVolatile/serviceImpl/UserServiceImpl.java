package com.example.BackendVolatile.serviceImpl;

import com.example.BackendVolatile.dao.stakeholder.EmployeeDAO;
import com.example.BackendVolatile.dao.UserDao.LoginLog;
import com.example.BackendVolatile.dao.UserDao.User;
import com.example.BackendVolatile.dao.stakeholder.EmployerDAO;
import com.example.BackendVolatile.dto.userDTO.LoginDTO;
import com.example.BackendVolatile.dto.userDTO.RegisterDTO;
import com.example.BackendVolatile.dto.userDTO.SetUserProfileDTO;
import com.example.BackendVolatile.mapper.user.LoginLogMapper;
import com.example.BackendVolatile.mapper.user.TaskFavorMapper;
import com.example.BackendVolatile.mapper.user.UserMapper;
import com.example.BackendVolatile.service.UserService;
import com.example.BackendVolatile.util.ActivityUtil;
import com.example.BackendVolatile.util.EncryptionUtils;
import com.example.BackendVolatile.util.ParameterValidityVerification;
import com.example.BackendVolatile.util.constant.*;
import com.example.BackendVolatile.util.jwtUtil.JwtTokenUtil;
import com.example.BackendVolatile.util.pythonUtil.prepareReportTrainingData.PrepareReportTrainingDataUtil;
import com.example.BackendVolatile.util.pythonUtil.prepareTaskRecommendationTrainingDataUtil.PrepareTaskRecommendationTrainingDataUtil;
import com.example.BackendVolatile.vo.ResultVO;
import com.example.BackendVolatile.vo.stakeholder.EmployeeStateVO;
import com.example.BackendVolatile.vo.stakeholder.EmployerStateVO;
import com.example.BackendVolatile.vo.userVO.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    public static final int DAY_LIMIT=10;

    @Resource
    private UserMapper userMapper;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private EncryptionUtils encryptionUtils;

    @Resource
    TaskFavorMapper taskFavorMapper;

    @Resource
    LoginLogMapper loginLogMapper;

    @Resource
    ParameterValidityVerification parameterValidityVerification;

    @Resource
    ActivityUtil activityUtil;



    @Resource
    PrepareTaskRecommendationTrainingDataUtil prepareTaskRecommendationTrainingDataUtil;

    @Resource
    PrepareReportTrainingDataUtil prepareReportTrainingDataUtil;


    /**
     * ????????????: ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * @param loginDTO
     * @return
     */
    @Override
    public LoginVO userLogin(LoginDTO loginDTO){
        LoginVO loginVO = new LoginVO();
        String phone_number = loginDTO.getPhone_number();
        String password = encryptionUtils.sha256Encrypt(loginDTO.getPassword());
        //rightPassword???????????????????????????????????????????????????????????????????????????
        Integer rightPassword = userMapper.right_password(phone_number,password);
        User user = userMapper.get_by_phone_number(phone_number);
        loginVO.setRole(user.getRole());
        loginVO.setNickname(user.getNick_name());
        loginVO.setToken(jwtTokenUtil.generateToken(loginVO,user.getUser_id()));
        loginVO.setResponse(new ResultVO(ResponseConstant.LOGIN_SUCCESS));

        LoginLog loginLog = new LoginLog();
        Long time = System.currentTimeMillis();
        loginLog.setLogin_time(time);
        loginLog.setUser_id(user.getUser_id());
        loginLog.setLogin_event(LoginEvent.LOGIN_SUCCESS);
        loginLog.setRole(user.getRole());
        loginLogMapper.insert(loginLog);
        return loginVO;
    }


    /**
     * ????????????: ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????token
     * @param registerDTO
     * @return
     */
    @Override
    public RegisterVO userRegister(RegisterDTO registerDTO){

        RegisterVO registerVO = new RegisterVO();
        //phoneNumberDontExist?????????????????????????????????????????????????????????????????????????????????
        Integer phoneNumberDontExist = userMapper.phone_number_dont_exist(registerDTO.getPhone_number());
        User user = new User(registerDTO);
        user.setPassword(encryptionUtils.sha256Encrypt(registerDTO.getPassword()));
        userMapper.insert(user);
        registerVO.setResponse(new ResultVO(ResponseConstant.REGISTRATION_SUCCEEDED));
        return registerVO;
    }

    /**
     * ?????????????????????????????????????????????????????????????????????????????????????????????
     * @param setUserProfileDTO
     * @return
     */
    @Override
    public SetUserProfileVO setUserProfile(SetUserProfileDTO setUserProfileDTO){
        SetUserProfileVO setUserProfileVO = new SetUserProfileVO();

        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            setUserProfileVO.setResponse(resultVO);
            return setUserProfileVO;
        }

        userMapper.update_professional_skill(userId,setUserProfileDTO.getProfessionalSkill());
        userMapper.update_device(userId,setUserProfileDTO.getDevice());
        userMapper.update_nick_name(userId,setUserProfileDTO.getNickname());
        taskFavorMapper.delete_task_favor_by_user_id(userId);
        List<String> taskFavorList = setUserProfileDTO.getTaskFavorList();
        for(int i = 0; i < taskFavorList.size(); i++){
            taskFavorMapper.insert(userId,taskFavorList.get(i));
        }

        setUserProfileVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_SET_USER_PROFILE_SUCCEEDED));
        return setUserProfileVO;
    }

    /**
     * ???????????????????????????????????????token??????????????????????????????????????????????????????????????????????????????
     * @return
     */
    @Override
    public GetUserDataVO getUserData(){
        GetUserDataVO getUserDataVO = new GetUserDataVO();

        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            getUserDataVO.setResponse(resultVO);
            return getUserDataVO;
        }

        List<String> task_favor_list = taskFavorMapper.get_task_favor_by_user_id(userId);
        getUserDataVO.setTaskFavorList(task_favor_list);
        User user = userMapper.get_by_id(userId);
        getUserDataVO.setDevice(user.getDevice());
        getUserDataVO.setNickname(user.getNick_name());
        getUserDataVO.setProfessionalSkill(user.getProfessional_skill());

        Long limit = System.currentTimeMillis() - (DAY_LIMIT + 1) * ActivityUtil.oneDayLong;
        List<Long> login_time = loginLogMapper.get_login_time_list_by_user_id_with_limit(userId,limit);

        getUserDataVO.setActiveDegree(activityUtil.calActivity(login_time,DAY_LIMIT));
        getUserDataVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_SUCCEEDED));
        return getUserDataVO;
    }

    @Override
    public List<EmployerStateVO> test() {
        return getPageOfAllEmployerStateInfo(10,0);
    }


    public List<EmployeeStateVO> getPageOfAllEmployeeStateInfo(Integer num, Integer offset){
        return null;
    }

    public List<EmployerStateVO> getPageOfAllEmployerStateInfo(Integer num, Integer offset) {
        return null;
    }
}
