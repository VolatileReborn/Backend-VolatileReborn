package com.example.BackendVolatile.mapper.report;


import com.example.BackendVolatile.dao.reportDAO.Report;
import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import org.apache.ibatis.annotations.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

/**
 * reports表的外键是user_id和task_id, 删除后两者会自动删除对应的report
 * 同样， defect_pictures表外键是report_id, 删除report会自动删除defect_pictures中对应的行
 */
@Validated(Default.class)
public interface ReportMapper {

    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
    @Select("SELECT * FROM VR_report.reports WHERE report_id = #{ report_id }")
    Report get_by_report_id(@Param("report_id") Long report_id );

    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
    @Select("SELECT task_id FROM VR_report.reports WHERE report_id = #{ report_id }")
    Long get_task_id_by_report_id(@Param("report_id") Long report_id );

    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
    @Select("SELECT report_state FROM VR_report.reports WHERE report_id = #{ report_id }")
    Integer get_report_state_by_report_id(@Param("report_id") Long report_id );

    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
    @Select("SELECT user_id FROM VR_report.reports WHERE report_id = #{ report_id }")
    Long get_user_id_by_report_id(@Param("report_id") Long report_id );

    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
    @Min( value = 1, message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
    @Select("SELECT COUNT(1) FROM VR_report.reports WHERE report_id = #{report_id}")
    Integer assert_report_id_exist(@Param("report_id")Long report_id);

    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
    @Min(value = 1, message = ParamFormatErrorConstant.REPORT_DONT_EXIST_OR_NOT_BELONG_TO_THE_TASK)
    @Select("SELECT COUNT(1) FROM VR_report.reports WHERE report_id = #{report_id} AND task_id = #{task_id}")
    Integer assert_report_belong_to_the_task(@Param("report_id")Long report_id, @Param("task_id")Long task_id);

    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
    @Select("SELECT COUNT(1) FROM VR_report.reports WHERE user_id = #{user_id} and task_id = #{task_id}")
    Integer report_id_in_it_by_user_id_and_task_id(@Param("user_id")Long user_id,@Param("task_id")Long task_id);

    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
    @Max(value = 0, message = ParamFormatErrorConstant.YOU_HAVE_SUBMITTED_THE_REPORT)
    @Select("SELECT COUNT(1) FROM VR_report.reports WHERE user_id = #{user_id} and task_id = #{task_id}")
    Integer assert_report_not_submitted(@Param("user_id")Long user_id,@Param("task_id")Long task_id);


    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
    @Min(value = 1, message = ParamFormatErrorConstant.YOU_HAVE_NOT_SUBMITTED_THE_REPORT)
    @Select("SELECT COUNT(1) FROM VR_report.reports WHERE user_id = #{user_id} and report_id = #{report_id}")
    Integer assert_report_is_submitted(@Param("user_id")Long user_id,@Param("report_id")Long report_id);

    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
    @Select("SELECT * FROM VR_report.reports WHERE user_id = #{user_id}" )
    List<Report> get_all_by_user_id(@Param("user_id")Long user_id );

    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
    @Select("SELECT * FROM VR_report.reports WHERE user_id = #{user_id} AND task_id = #{task_id}" )
    List<Report> get_all_by_user_id_and_task_id(
            @Param("user_id")Long user_id ,@Param("task_id")Long task_id);

    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
    @Select("SELECT * FROM VR_report.reports WHERE task_id = #{task_id}" )
    List<Report> get_all_by_task_id(@Param("task_id")Long task_id );



    @Options(useGeneratedKeys = true, keyProperty = "report_id", keyColumn = "report_id")
    @Insert("INSERT INTO VR_report.reports ( user_id, task_id, defect_explain,defect_reproduction_step," +
            "test_equipment_information,report_name,report_state) " +
            "VALUES ( #{report.user_id}, #{report.task_id},#{report.defect_explain}," +
            "#{report.defect_reproduction_step},#{report.test_equipment_information}, " +
            "#{report.report_name}, #{report.report_state})")
    void insert( @Param("report") Report report );

    @Update("UPDATE VR_report.reports SET similarity = #{similarity} WHERE report_id = #{report_id}")
    void update_similarity_by_report_id(@Param("report_id") Long report_id, @Param("similarity") Integer similarity);

    @Update("UPDATE VR_report.reports SET report_name = #{report.report_name}," +
            "defect_explain = #{report.defect_explain}, " +
            "defect_reproduction_step = #{report.defect_reproduction_step}, " +
            "test_equipment_information = #{report.test_equipment_information} " +
            " WHERE report_id = #{report_id}")
    void update_report_by_report_id(@Param("report") Report report, @Param("report_id") Long report_id);

    @Update("UPDATE VR_report.reports SET similar_report_id = #{similar_report_id} WHERE report_id = #{report_id}")
    void update_similar_report_id_by_report_id(@Param("report_id") Long report_id, @Param("similar_report_id") Long similar_report_id);
    /**
     * 删除报告。 注意：会把报告对应的缺陷截图也删除
     * @param report_id
     */
    @Delete( "DELETE FROM VR_report.reports where report_id = #{report_id}; ")
    void delete_by_report_id(@Param("report_id") Long report_id );


    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
    @Select("select count(*) from VR_report.reports;")
    Integer count_all();

    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
    @Select("select MAX(report_id) from VR_report.reports")
    Long max_id();

    /**
     * 得到输入的任务对应的总报告数
     * @param task_id
     * @return
     */
    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
    @Select("select count(*) from VR_report.reports WHERE task_id = #{task_id};")
    Integer count_reports_of_task( @Param("task_id") Long task_id);
}
