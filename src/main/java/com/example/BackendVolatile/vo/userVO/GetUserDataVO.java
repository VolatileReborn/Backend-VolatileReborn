package com.example.BackendVolatile.vo.userVO;

import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDataVO {
    private String nickname;
    private List<String> taskFavorList;
    private String device;
    private String professionalSkill;
    private ResultVO response;

    private Integer activeDegree;

    public void setTaskFavorList(List<String> taskFavorList){
        if(taskFavorList == null){
            this.taskFavorList = new ArrayList<>();
        }
        else{
            this.taskFavorList = taskFavorList;
        }
    }
}
