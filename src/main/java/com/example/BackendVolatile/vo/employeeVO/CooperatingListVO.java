package com.example.BackendVolatile.vo.employeeVO;

import com.example.BackendVolatile.dao.reportDAO.Report;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CooperatingListVO {

    private ResultVO response;

    private List<ReportBriefInfo> reportList;

    public void setReportList(List<Report> reportList, List<Integer> totalScoreList) {
        this.reportList = new ArrayList<>();
        assert reportList.size() == totalScoreList.size();
        for(int i = 0; i < reportList.size(); i++){
            ReportBriefInfo temp = new ReportBriefInfo(reportList.get(i));
            temp.setTotalScore(totalScoreList.get(i));
            this.reportList.add(temp);
        }
    }
}
