package com.example.BackendVolatile.vo.employeeVO;

import com.example.BackendVolatile.dao.taskDAO.ExecutableFile;
import com.example.BackendVolatile.dao.reportDAO.Report;
import com.example.BackendVolatile.dao.taskDAO.RequirementDescriptionFile;
import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BrowserTaskDetailVO {
    private ResultVO response;
    private Integer taskState;
    private Long reportId;
    private Integer workerNumTotal;
    private Integer workerNumLeft;
    private String taskName;
    private Long taskStartTime;
    private Long taskEndTime;
    private String taskIntroduction;
    private Integer taskType;
    private Integer isSubmitted;
    private List<File> requirementDescriptionFileList;
    private List<File> executableFileList;
    private Integer taskDifficulty;
    private Integer taskUrgency;
    private Boolean android;
    private Boolean ios;
    private Boolean linux;

    public void setExecutableFileList(List<ExecutableFile> executableFileList) {
        this.executableFileList = new ArrayList<>();
        for(int i=0;i<executableFileList.size();i++){
            File temp = new File(executableFileList.get(i).getExecutable_file_url(),
                    executableFileList.get(i).getExecutable_file_name());
            this.executableFileList.add(temp);
        }
    }

    public void setRequirementDescriptionFileList(List<RequirementDescriptionFile> requirementDescriptionFileList) {
        this.requirementDescriptionFileList = new ArrayList<>();
        for(int i=0;i<requirementDescriptionFileList.size();i++){
            File temp = new File(requirementDescriptionFileList.get(i).getFile_url(),
                    requirementDescriptionFileList.get(i).getFile_name());
            this.requirementDescriptionFileList.add(temp);
        }
    }

    public void setReportInfo(List<Report> reportInfo){//仅在已提交报告的情况下调用
        this.reportId = reportInfo.get(0).getReport_id();
    }

    public void setPartBrowserTaskDetailVO(Task task){
        this.taskEndTime = task.getEnd_time();
        this.taskStartTime = task.getBegin_time();
        this.taskState = task.getTask_state();
        this.workerNumTotal = task.getWorker_num_total();
        this.taskName = task.getTask_name();
        this.taskIntroduction = task.getIntroduction();
        this.taskType = task.getTask_type();
        this.workerNumLeft = task.getWorker_num_left();
        this.taskUrgency = task.getTask_urgency();
        this.taskDifficulty = task.getTask_difficulty();
        this.linux = task.getLinux();
        this.ios = task.getIos();
        this.android = task.getAndroid();


    }
}