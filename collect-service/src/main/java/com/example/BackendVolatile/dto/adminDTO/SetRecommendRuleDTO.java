package com.example.BackendVolatile.dto.adminDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetRecommendRuleDTO {

    @NotNull
    List<String> emphasized_user_features;
    @NotNull
    List<String> desalted_user_features;
    @NotNull
    List<String> emphasized_task_features;
    @NotNull
    List<String> desalted_task_features;
//    @Valid
//    private Rule recommendation_rules;

//
//
//    public List<String> getDesalted_task_features() {
//        return recommendation_rules.getDesalted_task_features();
//    }
//
//    public List<String> getEmphasized_task_features() {
//        return recommendation_rules.getEmphasized_task_features();
//    }
//
//    public List<String> getEmphasized_user_features() {
//        return recommendation_rules.getEmphasized_user_features();
//    }
//
//    public List<String> getDesalted_user_features() {
//        return recommendation_rules.desalted_user_features;
//    }

}
//
//@Data
//class Rule{
//
//
//}
