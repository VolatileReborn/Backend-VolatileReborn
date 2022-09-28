package com.example.BackendVolatile.serviceImpl;

import com.example.BackendVolatile.dao.taskDAO.ExecutableFile;
import com.example.BackendVolatile.dao.taskDAO.RequirementDescriptionFile;
import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.dto.adminDTO.BrowserTaskDetailDTO;
import com.example.BackendVolatile.dto.adminDTO.SetAlgorithmDTO;
import com.example.BackendVolatile.dto.adminDTO.SetChangeablePeriodDTO;
import com.example.BackendVolatile.dto.adminDTO.SetRecommendRuleDTO;
import com.example.BackendVolatile.mapper.report.AlgorithmMapper;
import com.example.BackendVolatile.mapper.report.RecommendationRuleMapper;
import com.example.BackendVolatile.mapper.task.ExecutableFileMapper;
import com.example.BackendVolatile.mapper.task.RequirementDescriptionFileMapper;
import com.example.BackendVolatile.mapper.task.TaskMapper;
import com.example.BackendVolatile.service.AdminService;
import com.example.BackendVolatile.util.ParameterValidityVerification;
import com.example.BackendVolatile.util.constant.*;
import com.example.BackendVolatile.vo.adminVO.*;
import com.example.BackendVolatile.vo.ResultVO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    TaskMapper taskMapper;

    @Resource
    ParameterValidityVerification parameterValidityVerification;

    @Resource
    ExecutableFileMapper executableFileMapper;

    @Resource
    RequirementDescriptionFileMapper requirementDescriptionFileMapper;

    @Resource
    AlgorithmMapper algorithmMapper;

    @Resource
    RecommendationRuleMapper recommendationRuleMapper;


    /**
     * 调用者：admin
     * 业务逻辑：管理员查看系统所有任务
     * @return
     */
    @Override
    public BrowserAllTasksVO browserAllTasks(){
        BrowserAllTasksVO browserAllTasksVO = new BrowserAllTasksVO();

        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.ADMIN.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());

        if(valid != BooleanValue.TRUE){
            browserAllTasksVO.setResponse(resultVO);
            return browserAllTasksVO;
        }

        List<Task> taskList = taskMapper.get_all_without_paging();
        browserAllTasksVO.setTaskList(taskList);
        browserAllTasksVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return browserAllTasksVO;
    }

    /**
     * 调用者：admin
     * 业务逻辑：管理员在个人中心查看任务细节
     * @param browserTaskDetailDTO
     * @return
     */
    @Override
    public BrowserTaskDetailVO browserTaskDetail(BrowserTaskDetailDTO browserTaskDetailDTO){
        BrowserTaskDetailVO browserTaskDetailVO = new BrowserTaskDetailVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.ADMIN.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());

        if(valid != BooleanValue.TRUE){
            browserTaskDetailVO.setResponse(resultVO);
            return browserTaskDetailVO;
        }

        Long taskId = browserTaskDetailDTO.getTaskId();
        Task task = taskMapper.get_by_task_id(taskId);
        browserTaskDetailVO.setTaskPartDetail(task);

        List<ExecutableFile> executableFileList =
                executableFileMapper.get_all_by_task_id_without_paging(taskId);
        browserTaskDetailVO.setExecutableFileList(executableFileList);

        List<RequirementDescriptionFile> requirementDescriptionFileList =
                requirementDescriptionFileMapper.get_all_by_task_id_without_paging(taskId);
        browserTaskDetailVO.setRequirementDescriptionFileList(requirementDescriptionFileList);

        browserTaskDetailVO.setResponse(new ResultVO(ResponseConstant.EMPLOYER_SUCCEEDED));
        return browserTaskDetailVO;
    }


    /**
     * 管理员设置任务推荐规则
     * @param setRecommendRuleDTO
     * @return
     */
    @Override
    public SetRecommendRuleVO setRecommendRule(SetRecommendRuleDTO setRecommendRuleDTO){
        SetRecommendRuleVO setRecommendRuleVO = new SetRecommendRuleVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.ADMIN.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());

        if(valid != BooleanValue.TRUE){
            setRecommendRuleVO.setResponse(resultVO);
            return setRecommendRuleVO;
        }
        recommendationRuleMapper.delete_all();
        List<String> eu = setRecommendRuleDTO.getEmphasized_user_features();
        List<String> du = setRecommendRuleDTO.getDesalted_user_features();
        List<String> et = setRecommendRuleDTO.getEmphasized_task_features();
        List<String> dt = setRecommendRuleDTO.getDesalted_task_features();

        for(int i = 0; i < eu.size();i++){
            recommendationRuleMapper.insert(eu.get(i), RecommendationRuleClassificationConstant.EMPHASIZED_USER_FEATURES);
        }
        for(int i = 0; i < du.size();i++){
            recommendationRuleMapper.insert(du.get(i), RecommendationRuleClassificationConstant.DESALTED_USER_FEATURES);
        }
        for(int i = 0; i < et.size();i++){
            recommendationRuleMapper.insert(et.get(i), RecommendationRuleClassificationConstant.EMPHASIZED_TASK_FEATURES);
        }
        for(int i = 0; i < dt.size();i++){
            recommendationRuleMapper.insert(dt.get(i), RecommendationRuleClassificationConstant.DESALTED_TASK_FEATURES);
        }
        setRecommendRuleVO.setResponse(new ResultVO(ResponseConstant.ADMIN_SET_RECOMMENDATION_RULE_SUCCESS));
        return setRecommendRuleVO;
    }

    /**
     * 管理员获取任务推荐规则
     * @return
     */
    @Override
    public GetRecommendRuleVO getRecommendRule(){
        GetRecommendRuleVO getRecommendRuleVO = new GetRecommendRuleVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.ADMIN.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());

        if(valid != BooleanValue.TRUE){
            getRecommendRuleVO.setResponse(resultVO);
            return getRecommendRuleVO;
        }

        System.out.println(recommendationRuleMapper.get_feature_by_classification(
                RecommendationRuleClassificationConstant.EMPHASIZED_USER_FEATURES) == null);
        getRecommendRuleVO.setEmphasizedUserFeatures(recommendationRuleMapper.get_feature_by_classification(
                RecommendationRuleClassificationConstant.EMPHASIZED_USER_FEATURES));

        getRecommendRuleVO.setDesaltedUserFeatures(recommendationRuleMapper.get_feature_by_classification(
                RecommendationRuleClassificationConstant.DESALTED_USER_FEATURES));

        getRecommendRuleVO.setEmphasizedTaskFeatures(recommendationRuleMapper.get_feature_by_classification(
                RecommendationRuleClassificationConstant.EMPHASIZED_TASK_FEATURES));

        getRecommendRuleVO.setDesaltedTaskFeatures(recommendationRuleMapper.get_feature_by_classification(
                RecommendationRuleClassificationConstant.DESALTED_TASK_FEATURES));

        getRecommendRuleVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return getRecommendRuleVO;
    }

    /**
     * 管理员计算相似度推荐算法
     * @param setAlgorithmDTO
     * @return
     */
    @Override
    public SetAlgorithmVO setAlgorithm(SetAlgorithmDTO setAlgorithmDTO){
        SetAlgorithmVO setAlgorithmVO = new SetAlgorithmVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.ADMIN.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            setAlgorithmVO.setResponse(resultVO);
            return setAlgorithmVO;
        }
        algorithmMapper.delete_all();
        algorithmMapper.insert(userId,setAlgorithmDTO.getRule());
        setAlgorithmVO.setResponse(new ResultVO(ResponseConstant.ADMIN_SET_RULE_SUCCESS));

        return setAlgorithmVO;
    }


    /**
     * 管理员查看相似度计算方法
     * @return
     */
    @Override
    public GetAlgorithmVO getAlgorithm(){
        GetAlgorithmVO getAlgorithmVO = new GetAlgorithmVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.ADMIN.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());

        if(valid != BooleanValue.TRUE){
            getAlgorithmVO.setResponse(resultVO);
            return getAlgorithmVO;
        }

//        getAlgorithmVO.setRule(algorithmMapper.get_algorithm_by_admin_id(1L));
        getAlgorithmVO.setRule(algorithmMapper.get_algorithm());
        getAlgorithmVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return getAlgorithmVO;
    }

    /**
     * 查看报告可修改时间
     * @return
     */
    @Override
    public GetChangeablePeriodVO getChangeablePeriod(){
        GetChangeablePeriodVO getChangeablePeriodVO = new GetChangeablePeriodVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.ADMIN.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());

        if(valid != BooleanValue.TRUE){
            getChangeablePeriodVO.setResponse(resultVO);
            return getChangeablePeriodVO;
        }
        getChangeablePeriodVO.setChangeablePeriod(algorithmMapper.get_day().get(0));
        getChangeablePeriodVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return getChangeablePeriodVO;
    }

    @Override
    public SetChangeablePeriodVO setChangeablePeriod(SetChangeablePeriodDTO setChangeablePeriodDTO){
        SetChangeablePeriodVO setChangeablePeriodVO = new SetChangeablePeriodVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.ADMIN.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());

        if(valid != BooleanValue.TRUE){
            setChangeablePeriodVO.setResponse(resultVO);
            return setChangeablePeriodVO;
        }
        algorithmMapper.delete_day();
        algorithmMapper.insert_day(setChangeablePeriodDTO.getChangeablePeriod());
        setChangeablePeriodVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return setChangeablePeriodVO;
    }

}
