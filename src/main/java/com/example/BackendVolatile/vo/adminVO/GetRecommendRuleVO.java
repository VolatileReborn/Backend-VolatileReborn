package com.example.BackendVolatile.vo.adminVO;

import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class GetRecommendRuleVO {
    private ResultVO response;

    private Rule recommendation_rules;

    public void setEmphasizedUserFeatures(List<String> featureList){
        this.recommendation_rules.setEmphasized_user_features(featureList);
    }
    public void setDesaltedUserFeatures(List<String> featureList){
        this.recommendation_rules.setDesalted_user_features(featureList);
    }

    public void setEmphasizedTaskFeatures(List<String> featureList){
        this.recommendation_rules.setEmphasized_task_features(featureList);
    }

    public void setDesaltedTaskFeatures(List<String> featureList){
        this.recommendation_rules.setDesalted_task_features(featureList);

    }

    public GetRecommendRuleVO(){
        this.recommendation_rules  = new Rule();
    }



}

@Data
class Rule{
    List<String> emphasized_user_features;
    List<String> desalted_user_features;
    List<String> emphasized_task_features;
    List<String> desalted_task_features;

    public Rule(){
        this.emphasized_task_features = new ArrayList<>();
        this.desalted_task_features = new ArrayList<>();
        this.emphasized_user_features = new ArrayList<>();
        this.desalted_user_features = new ArrayList<>();
    }
}