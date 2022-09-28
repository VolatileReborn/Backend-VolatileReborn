package com.example.BackendVolatile.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScoreUtil {

    public Integer calTotalScore(List<Integer> scoreList){
        Long count = 0L;
        int num = scoreList.size();
        if(num == 0){
            return 0;
        }
        for(int i=0;i<num;i++){
            count += scoreList.get(i);
        }

        return  (int) (count / num);
    }
}
