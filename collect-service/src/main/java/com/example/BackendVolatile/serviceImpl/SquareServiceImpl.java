package com.example.BackendVolatile.serviceImpl;

import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.dto.squareDTO.EmployeeTaskDetailDTO;
import com.example.BackendVolatile.dto.squareDTO.TaskDetailDTO;
import com.example.BackendVolatile.mapper.task.SelectTaskMapper;
import com.example.BackendVolatile.mapper.task.TaskMapper;
import com.example.BackendVolatile.service.EmployerService;
import com.example.BackendVolatile.service.SquareService;
import com.example.BackendVolatile.util.ParameterValidityVerification;
import com.example.BackendVolatile.util.constant.*;
import com.example.BackendVolatile.util.pythonUtil.getRecommendedTaskUtil.GetRecommendedTaskUtil;
import com.example.BackendVolatile.util.pythonUtil.prepareTaskRecommendationTrainingDataUtil.PrepareTaskRecommendationTrainingDataUtil;
import com.example.BackendVolatile.util.pythonUtil.prepareTrainingDataUtil.PrepareTrainingDataUtil;
import com.example.BackendVolatile.vo.ResultVO;
import com.example.BackendVolatile.vo.employerVO.BrowserCompositeTasksVO;
import com.example.BackendVolatile.vo.employerVO.CompositeTaskStateVO;
import com.example.BackendVolatile.vo.squareVO.BrowserTasksVO;
import com.example.BackendVolatile.vo.squareVO.VisitorBrowserTasksVO;
import com.example.BackendVolatile.vo.squareVO.EmployeeTaskDetailVO;
import com.example.BackendVolatile.vo.squareVO.TaskDetailVO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SquareServiceImpl implements SquareService {

    @Resource
    EmployerService employerService;

    @Resource
    TaskMapper taskMapper;

    @Resource
    SelectTaskMapper selectTaskMapper;

    @Resource
    ParameterValidityVerification parameterValidityVerification;

    @Resource
    PrepareTrainingDataUtil prepareTrainingDataUtil;

    @Resource
    RestTemplate restTemplate;

    @Resource
    GetRecommendedTaskUtil getRecommendedTaskUtil;

    @Resource
    PrepareTaskRecommendationTrainingDataUtil pTaskRecommend;

    /**
     * ?????????????????????????????????
     * ??????????????????????????????????????????????????????????????????????????????????????????????????????
     */
    @Override
    public VisitorBrowserTasksVO visitorBrowserTasks(){
        VisitorBrowserTasksVO visitorBrowserTasksVO = new VisitorBrowserTasksVO();
        List<Task> taskList = taskMapper.get_one_state_all_tasks_without_paging(TaskStateConstant.UNDERTAKING.getCode());
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if(taskList.get(i).getWorker_num_left() == 0){
                indexList.add(i);
            }
        }
        for (int i = indexList.size() - 1; i >= 0; i--) {
            taskList.remove((int) indexList.get(i));
        }
        visitorBrowserTasksVO.setTaskList(taskList);
        visitorBrowserTasksVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return visitorBrowserTasksVO;
    }

    /**
     * ????????????employee
     * ????????????????????????????????????????????????????????????????????????????????????????????????????????????
     */
    @Override
    public BrowserTasksVO browserTasks(){// ?????????????????????
//        BrowserTasksVO browserTasksVO = new BrowserTasksVO();
//        List<Task> taskList = taskMapper.get_one_state_all_tasks_without_paging(TaskStateConstant.UNDERTAKING.getCode());
//        List<Integer> indexList = new ArrayList<>();
//        for (int i = 0; i < taskList.size(); i++) {
//            if(taskList.get(i).getWorker_num_left() == 0){
//                indexList.add(i);
//            }
//        }
//        for (int i = indexList.size() - 1; i >= 0; i--) {
//            taskList.remove((int) indexList.get(i));
//        }
//        browserTasksVO.setTaskList(taskList);
//        browserTasksVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
//        return browserTasksVO;
//        return visitorBrowserTasksVO;

            BrowserTasksVO browserTasksVO = new BrowserTasksVO();

            Map<String, Object> tokenVerification =
                    parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
            ResultVO resultVO = (ResultVO) tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
            Long valid = (Long) tokenVerification.get(VerificationMapConstant.VALID.getStr());
            Long userId = (Long) tokenVerification.get(VerificationMapConstant.USERID.getStr());

            if (valid != BooleanValue.TRUE) {
                browserTasksVO.setResponse(resultVO);
                return browserTasksVO;
            }

            List<Task> taskList = taskMapper.get_one_state_all_tasks_without_paging(TaskStateConstant.UNDERTAKING.getCode());

            BrowserCompositeTasksVO browserCompositeTasksVO=employerService.getCompositeTasksWithoutValidation(1,Integer.MAX_VALUE,null);
            System.err.println(browserCompositeTasksVO);
            List<CompositeTaskStateVO> compositeTaskStateVOList=browserCompositeTasksVO.getCompositeTaskStateList();
            for(CompositeTaskStateVO compositeTaskState:compositeTaskStateVOList) {
                List<CompositeTaskStateVO.InnerSubTaskVO> subTaskVOList = compositeTaskState.getSubTasks();
                for (CompositeTaskStateVO.InnerSubTaskVO subTask : subTaskVOList) {
                    if (subTask.getTaskState() != CompositeTaskStateVO.SubTaskState.RECRUITING) {
                        for(int index=0;index<taskList.size();index++){
                            if(taskList.get(index).getTask_id().equals(subTask.getTaskId())){
                                taskList.remove(index);
                                break;
                            }
                        }
                    }
                }
            }

            List<Integer> indexList = new ArrayList<>();
            for (int i = 0; i < taskList.size(); i++) {
                if (selectTaskMapper.is_selected(taskList.get(i).getTask_id(), userId) == BooleanValue.TRUE) {
                    indexList.add(i);
                }
                else if(taskList.get(i).getWorker_num_left() == 0){
                    indexList.add(i);
                }
            }
            for (int i = indexList.size() - 1; i >= 0; i--) {
                taskList.remove((int) indexList.get(i));
            }
            browserTasksVO.setTaskList(taskList);
            browserTasksVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
            return browserTasksVO;
//            try {
//            //?????????????????????
//            //
//                String prepareUrl = "http://"+PythonServerConstant.IP+ ":"+PythonServerConstant.PORT+"/prepareTaskRecommendationTrainingData";
//                PrepareTaskRecommendationTrainingDataDTO pDTO = pTaskRecommend.getPrepareTaskRecommendationTrainingDataDTO();
//                String s = restTemplate.postForObject(prepareUrl, pDTO, String.class);
//                System.out.println(s);
//            if (s == null || !s.equals("\"Task Data Prepared Successfully\"")) {
//                browserTasksVO.setTaskList(taskList);
//                browserTasksVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
//                return browserTasksVO;
//            } else {
//                System.out.println("????????????-------------");
//                String gurl = "http://"+PythonServerConstant.IP+ ":"+PythonServerConstant.PORT+"/getRecommendedTasks";
//                GetRecommendedTaskDTO getRecommendedTaskDTO = getRecommendedTaskUtil.getRecommendedTask(userId);
//                System.out.println(getRecommendedTaskDTO.toString());
//                String res = restTemplate.postForObject(gurl, getRecommendedTaskDTO, String.class);
//                if(res.startsWith("[{")){
//                    System.out.println("res" + res);
//                    List<Long> rcmdTaskIdList = getRecommendedTaskUtil.parse(res);
//                    System.out.println(rcmdTaskIdList.toString());
//                    List<Task> rcmdTaskList = new ArrayList<>();
//                    System.out.println("?????????:" + rcmdTaskList.toString());
//                    for(int i=0;i<rcmdTaskIdList.size();i++){
//                        if(taskMapper.task_id_exist(rcmdTaskIdList.get(i)) == BooleanValue.TRUE &&
//                                taskMapper.get_state_by_task_id(rcmdTaskIdList.get(i)) == TaskStateConstant.UNDERTAKING.getCode() &&
//                              taskMapper.get_worker_num_left_by_task_id(rcmdTaskIdList.get(i)) !=0){
//                            rcmdTaskList.add(taskMapper.get_by_task_id(rcmdTaskIdList.get(i)));
//                        }
//                    }
//                    List<Integer> remove = new ArrayList<>();
//                    for(int i=0;i<taskList.size();i++){
//                        if(rcmdTaskIdList.contains(taskList.get(i).getTask_id())){
//                            remove.add(i);
//                        }
//                    }
//                    for(int i=remove.size()-1;i>=0;i--){
//                        taskList.remove((int)remove.get(i));
//                    }
//                    System.out.println("?????????:" + rcmdTaskList.toString());
//                    rcmdTaskList.addAll(taskList);
//                    browserTasksVO.setTaskList(rcmdTaskList);
//                    System.out.println("????????????:"  + rcmdTaskList.toString());
//                    browserTasksVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
//                    return browserTasksVO;
//                }
//                else{
//                    browserTasksVO.setTaskList(taskList);
//                    browserTasksVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
//                    return browserTasksVO;
//                }
//            }
//        }catch (Exception e){
//                browserTasksVO.setTaskList(taskList);
//                browserTasksVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
//                return browserTasksVO;
//        }

    }

    /**
     * ????????????employee
     * ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     */
    @Override
    public EmployeeTaskDetailVO employeeTaskDetail(EmployeeTaskDetailDTO employeeTaskDetailDTO){
        EmployeeTaskDetailVO employeeTaskDetailVO = new EmployeeTaskDetailVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            employeeTaskDetailVO.setResponse(resultVO);
            return employeeTaskDetailVO;
        }

        Long taskId = employeeTaskDetailDTO.getTaskId();
        Task task = taskMapper.get_by_task_id(taskId);
        employeeTaskDetailVO.setTask(task);
        employeeTaskDetailVO.setIsSelected(selectTaskMapper.is_selected(taskId,userId));
        employeeTaskDetailVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return employeeTaskDetailVO;
    }

    /**
     * ????????????employer/visitor/admin
     * ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     */
    @Override
    public TaskDetailVO taskDetail(TaskDetailDTO taskDetailDTO){
        TaskDetailVO taskDetailVO =  new TaskDetailVO();
        Long taskId = taskDetailDTO.getTaskId();
        Task task = taskMapper.get_by_task_id(taskId);//?????????????????????????????????
        taskDetailVO.setTask(task);
        taskDetailVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return taskDetailVO;
    }

}
