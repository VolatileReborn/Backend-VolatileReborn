package com.example.BackendVolatile.vo.reportVO;

import com.example.BackendVolatile.dao.reportDAO.CooperationReport;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class TreeNode{
    private Long name;
    private String value;
    private List<Leaf> children;

    public TreeNode(){
        this.children = new ArrayList<>();
    }

    public void setChildren(List<CooperationReport> cooperationReportList, Long id) {
        this.children = new ArrayList<>();
        for(int i=0;i<cooperationReportList.size();i++){
            String reportName = cooperationReportList.get(i).getReport_name();
            Leaf leaf = new Leaf(id,reportName);
            id++;
            this.children.add(leaf);
        }
    }
}

@Data
@AllArgsConstructor
class Leaf{
    private Long name;
    private String value;

}