package com.example.BackendVolatile.util.pythonUtil;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.example.BackendVolatile.util.JSONUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PythonUtil {
    public ClusterReportsDTO getClusterReportsDTO(Long reportId, Long taskId){
        return new ClusterReportsDTO(reportId,taskId);
    }

    public RecommendedReportsDTO getRecommendedReportsDTO(
            Long reportId, Long taskId, Integer num, String algorithm){
        return new RecommendedReportsDTO(reportId,taskId,num,algorithm);
    }

    public SimilarReportsDTO getSimilarReportsDTO(
            Long reportId, Long taskId, Integer num, String algorithm){
        return new SimilarReportsDTO(reportId,taskId,num,algorithm);
    }

    public List<ClusterReportVO> parseCluster(String s){
        List<ClusterReportVO> clusters = new ArrayList<>();
        if(s == null || s.equals("")){
            return clusters;
        }
        boolean right = s.charAt(0) == '[' && s.charAt(s.length()-1) ==']';
        if(!right){
            return clusters;
        }
        String splitString = "}, ";
        String[] itemList = s.substring(1,s.length()-2).split(splitString);
        for(int i=0;i<itemList.length;i++){
            System.out.println(itemList[i]);
            ClusterReportVO clusterReportVO = JSONUtils.jsonToPojo(itemList[i] + "}",ClusterReportVO.class);
            clusters.add(clusterReportVO);
        }
        return clusters;
    }


}

@Data
@AllArgsConstructor
class ClusterReportsDTO{
    private Long report_id;
    private Long task_id;
}

@Data
@AllArgsConstructor
class RecommendedReportsDTO{
    private Long report_id;
    private Long task_id;
    private Integer recommended_report_num;
    private String algorithm;

}

@Data
@AllArgsConstructor
class SimilarReportsDTO{
    private Long report_id;
    private Long task_id;
    private Integer similar_report_num;
    private String algorithm;
}

