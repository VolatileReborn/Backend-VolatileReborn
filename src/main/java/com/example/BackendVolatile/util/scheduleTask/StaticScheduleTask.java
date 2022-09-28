package com.example.BackendVolatile.util.scheduleTask;

import com.example.BackendVolatile.mapper.task.TaskMapper;
import com.example.BackendVolatile.util.constant.PythonServerConstant;
import com.example.BackendVolatile.util.pythonUtil.prepareReportTrainingData.PrepareReportTrainingDataDTO;
import com.example.BackendVolatile.util.pythonUtil.prepareReportTrainingData.PrepareReportTrainingDataUtil;
import com.example.BackendVolatile.util.pythonUtil.prepareTaskRecommendationTrainingDataUtil.PrepareTaskRecommendationTrainingDataDTO;
import com.example.BackendVolatile.util.pythonUtil.prepareTaskRecommendationTrainingDataUtil.PrepareTaskRecommendationTrainingDataUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class StaticScheduleTask {
    @Resource
    TaskMapper taskMapper;

    @Resource
    PrepareReportTrainingDataUtil prepareReportTrainingDataUtil;

    @Resource
    PrepareTaskRecommendationTrainingDataUtil prepareTaskRecommendationTrainingDataUtil;

    @Resource
    RestTemplate restTemplate;

//    //3.添加定时任务
////    @Scheduled(cron = "0/5 * * * * ?")
//    //或直接指定时间间隔，例如：5秒
//    @Scheduled(fixedRate=3600000)
//    private void configureTasks() {
//
//                System.err.println("执行定时任务时间:准备报告数据: " + LocalDateTime.now());
//                String url = "http://"+ PythonServerConstant.IP+ ":"+PythonServerConstant.PORT+"/prepareReportTrainingData";
//                System.out.println(url);
//                List<Long> taskIdList = taskMapper.get_all_task_id();
//                for(int i=0;i<taskIdList.size();i++){
//                    try{
//                    Long taskId = taskIdList.get(i);
//                    System.out.println("taskId: " + taskId);
//                    PrepareReportTrainingDataDTO pDTO = prepareReportTrainingDataUtil.getPrepareReportTrainingDataDTO(taskId,"DeepPrior");
//                    if(pDTO.getReport_list().size()>0){
//                        String s = restTemplate.postForObject(url, pDTO, String.class);
//                        System.out.println("report behind");
//                        System.out.println("report: " + s);
//                    }
//                    else{
//                        System.out.println("该任务未提交报告");
//                    }
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//
//
//
//
//    }

//    @Scheduled(fixedRate = 50000)
//    private void recommend(){
//        System.err.println("执行定时任务时间:准备推荐数据: " + LocalDateTime.now());
//        try {
//
//            String url = "http://"+PythonServerConstant.IP+ ":"+PythonServerConstant.PORT+
//                    "/prepareTaskRecommendationTrainingData";
//            System.out.println(url);
//            PrepareTaskRecommendationTrainingDataDTO pDTO
//                    = prepareTaskRecommendationTrainingDataUtil.getPrepareTaskRecommendationTrainingDataDTO();
//
//            String s = restTemplate.postForObject(url, pDTO, String.class);
//            System.out.println("recommend behind");
//            System.out.println("recommend: " + s);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}