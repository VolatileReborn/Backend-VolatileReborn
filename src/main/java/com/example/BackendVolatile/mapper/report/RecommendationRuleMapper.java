package com.example.BackendVolatile.mapper.report;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@Validated(Default.class)
public interface RecommendationRuleMapper {

    @Insert("INSERT INTO recommendation_rules (feature, classification) VALUES (#{feature}, #{classification})")
    void insert(@Param("feature") String feature, @Param("classification") String classification);


    @NotNull
    @Select("SELECT feature FROM recommendation_rules WHERE classification = #{classification}")
    List<String> get_feature_by_classification(@Param("classification") String classification);

    @Delete("DELETE FROM recommendation_rules")
    void delete_all();
}
