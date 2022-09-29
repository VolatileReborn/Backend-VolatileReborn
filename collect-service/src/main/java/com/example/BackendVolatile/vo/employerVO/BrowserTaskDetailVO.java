package com.example.BackendVolatile.vo.employerVO;

import com.example.BackendVolatile.dao.taskDAO.ExecutableFile;
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
    private Integer workerNumTotal;
    private Integer taskState;
    private Integer workerNumLeft;
    private String taskName;
    private String taskIntroduction;
    private Integer type;
    private Long beginTime;
    private Long endTime;
    private List<InnerFile> requirementDescriptionFileList;
    private List<InnerFile> executableFileList;
    private Integer taskDifficulty;
    private Integer taskUrgency;
    private Boolean android;
    private Boolean ios;
    private Boolean linux;



    public void setTaskPartDetail(Task task){
        this.endTime = task.getEnd_time();
        this.beginTime = task.getBegin_time();
        this.taskState = task.getTask_state();
        this.workerNumTotal = task.getWorker_num_total();
        this.taskName = task.getTask_name();
        this.taskIntroduction = task.getIntroduction();
        this.type = task.getTask_type();
        this.workerNumLeft = task.getWorker_num_left();
        this.taskUrgency = task.getTask_urgency();
        this.taskDifficulty = task.getTask_difficulty();
        this.linux = task.getLinux();
        this.ios = task.getIos();
        this.android = task.getAndroid();

    }


    public void setExecutableFileList(List<ExecutableFile> executableFileList) {
        this.executableFileList = new ArrayList<>();
        for(int i = 0; i < executableFileList.size(); i++){
            InnerFile temp = new InnerFile(executableFileList.get(i).getExecutable_file_url(),
                    executableFileList.get(i).getExecutable_file_name());
            this.executableFileList.add(temp);
        }
    }

    public void setRequirementDescriptionFileList(List<RequirementDescriptionFile> requirementDescriptionFileList) {
        this.requirementDescriptionFileList = new ArrayList<>();
        for(int i=0;i<requirementDescriptionFileList.size();i++){
            InnerFile temp = new InnerFile(requirementDescriptionFileList.get(i).getFile_url(),
                    requirementDescriptionFileList.get(i).getFile_name());
            this.requirementDescriptionFileList.add(temp);
        }
    }



}

@Data
@AllArgsConstructor
class InnerFile {
    private String fileURL;
    private String fileName;
}
