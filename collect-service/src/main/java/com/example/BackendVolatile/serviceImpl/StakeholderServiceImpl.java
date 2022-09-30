package com.example.BackendVolatile.serviceImpl;

import com.example.BackendVolatile.dao.stakeholder.EmployeeDAO;
import com.example.BackendVolatile.dao.stakeholder.EmployerDAO;
import com.example.BackendVolatile.mapper.task.DeviceMapper;
import com.example.BackendVolatile.mapper.user.LoginLogMapper;
import com.example.BackendVolatile.mapper.user.TaskFavorMapper;
import com.example.BackendVolatile.mapper.user.UserMapper;
import com.example.BackendVolatile.service.StakeholderService;
import com.example.BackendVolatile.util.ActivityUtil;
import com.example.BackendVolatile.util.ParameterValidityVerification;
import com.example.BackendVolatile.util.constant.BooleanValue;
import com.example.BackendVolatile.util.constant.RoleConstant;
import com.example.BackendVolatile.util.constant.TaskStateConstant;
import com.example.BackendVolatile.util.constant.VerificationMapConstant;
import com.example.BackendVolatile.vo.ResultVO;
import com.example.BackendVolatile.vo.stakeholder.*;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.BackendVolatile.serviceImpl.UserServiceImpl.DAY_LIMIT;

@Service
public class StakeholderServiceImpl implements StakeholderService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private DeviceMapper deviceMapper;

    @Resource
    private LoginLogMapper loginLogMapper;

    @Resource
    private TaskFavorMapper taskFavorMapper;

    @Resource
    private ActivityUtil activityUtil;

    @Resource
    private ParameterValidityVerification parameterValidityVerification;

    static class ValidationResult {
        ResultVO resultVO;
        Long valid;

        ValidationResult(Long valid, ResultVO resultVO) {
            this.resultVO = resultVO;
            this.valid = valid;
        }
    }

    private ValidationResult validatePermission() {
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.ADMIN.getRole());
        return new ValidationResult((Long) tokenVerification.get(VerificationMapConstant.VALID.getStr()),
                (ResultVO) tokenVerification.get(VerificationMapConstant.RESULTVO.getStr()));
    }

    @Override
    public BrowserEmployersVO getEmployerStates(Integer pageNum, Integer pageSize) {
        BrowserEmployersVO res = new BrowserEmployersVO();
        ValidationResult validationResult = validatePermission();
        res.setResponse(validationResult.resultVO);
        if (validationResult.valid != BooleanValue.TRUE) return res;

        List<EmployerDAO> employerDAOList = userMapper.get_all_employer(RoleConstant.EMPLOYER.getRole(), pageSize, (pageNum - 1) * pageSize);
        res.setEmployerList(employerDAOList.stream().map(employer -> {
            Long limit = System.currentTimeMillis() - (DAY_LIMIT + 1) * ActivityUtil.oneDayLong;
            List<Long> login_time = loginLogMapper.get_login_time_list_by_user_id_with_limit(employer.getUserId(), limit);
            Integer activeDegree = activityUtil.calActivity(login_time, DAY_LIMIT);

            List<Integer> allDifficulties = userMapper.get_all_difficulty_of_tasks_of_employer(employer.getUserId());
            List<String> allTaskTypes = userMapper.get_all_type_of_tasks_of_employer(employer.getUserId());

            Map<Integer, Integer> numOfEachTaskDifficulty = new HashMap<>();
            Map<String, Integer> numOfEachTaskType = new HashMap<>();
            for (Integer difficulty : allDifficulties)
                numOfEachTaskDifficulty.put(difficulty, numOfEachTaskDifficulty.getOrDefault(difficulty, 0) + 1);
            for (String type : allTaskTypes) numOfEachTaskType.put(type, numOfEachTaskType.getOrDefault(type, 0) + 1);
            return new EmployerStateVO(employer, numOfEachTaskDifficulty, numOfEachTaskType, activeDegree);
        }).collect(Collectors.toList()));

        return res;
    }

    @Override
    public BrowserEmployeesVO getEmployeeStates(Integer pageNum, Integer pageSize) {
        BrowserEmployeesVO res = new BrowserEmployeesVO();
        ValidationResult validationResult = validatePermission();
        res.setResponse(validationResult.resultVO);
        if (validationResult.valid != BooleanValue.TRUE) return res;

        List<EmployeeDAO> employeeDAOList = userMapper.get_all_employee(RoleConstant.EMPLOYEE.getRole(), pageSize, (pageNum - 1) * pageSize);
        res.setEmployeeList(employeeDAOList.stream().map(employee -> {
            List<String> task_favor_list = taskFavorMapper.get_task_favor_by_user_id(employee.getUserId());

            Long limit = System.currentTimeMillis() - (DAY_LIMIT + 1) * ActivityUtil.oneDayLong;
            List<Long> login_time = loginLogMapper.get_login_time_list_by_user_id_with_limit(employee.getUserId(), limit);
            Integer activeDegree = activityUtil.calActivity(login_time, DAY_LIMIT);

            List<Integer> allScores = userMapper.get_all_scores_of_reports_of_employee(employee.getUserId());
            List<Integer> allDifficulties = userMapper.get_all_difficulty_of_tasks_of_employee(employee.getUserId());
            List<String> allTaskTypes = userMapper.get_all_type_of_tasks_of_employee(employee.getUserId());

            Map<Integer, Integer> numOfEachScore = new HashMap<>(), numOfEachTaskDifficulty = new HashMap<>();
            Map<String, Integer> numOfEachTaskType = new HashMap<>();
            for (Integer score : allScores) numOfEachScore.put(score, numOfEachScore.getOrDefault(score, 0) + 1);
            for (Integer difficulty : allDifficulties)
                numOfEachTaskDifficulty.put(difficulty, numOfEachTaskDifficulty.getOrDefault(difficulty, 0) + 1);
            for (String type : allTaskTypes) numOfEachTaskType.put(type, numOfEachTaskType.getOrDefault(type, 0) + 1);

            return new EmployeeStateVO(employee, task_favor_list, numOfEachScore, numOfEachTaskDifficulty, numOfEachTaskType, activeDegree);
        }).collect(Collectors.toList()));
        return res;
    }

    @Override
    public BrowserDevicesVO getDeviceStates(Integer pageNum, Integer pageSize) {
        BrowserDevicesVO res = new BrowserDevicesVO();
        ValidationResult validationResult = validatePermission();
        res.setResponse(validationResult.resultVO);
        if (validationResult.valid != BooleanValue.TRUE) return res;

        res.setDeviceList(new ArrayList<>(Arrays.asList(
                new DeviceStateVO("android", deviceMapper.get_user_num_of_device("android"),
                        deviceMapper.get_num_of_tasks_can_be_finished_by_android(),
                        deviceMapper.get_num_of_recruiting_tasks_can_be_finished_by_android(TaskStateConstant.UNDERTAKING.getCode())),
                new DeviceStateVO("ios", deviceMapper.get_user_num_of_device("ios"),
                        deviceMapper.get_num_of_tasks_can_be_finished_by_ios(),
                        deviceMapper.get_num_of_recruiting_tasks_can_be_finished_by_ios(TaskStateConstant.UNDERTAKING.getCode())),
                new DeviceStateVO("linux", deviceMapper.get_user_num_of_device("linux"),
                        deviceMapper.get_num_of_tasks_can_be_finished_by_linux(),
                        deviceMapper.get_num_of_recruiting_tasks_can_be_finished_by_linux(TaskStateConstant.UNDERTAKING.getCode()))
        )));
        return res;
    }
}
