package com.example.BackendVolatile.mapper.report;

import com.example.BackendVolatile.dao.reportDAO.CooperationReport;
import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import org.apache.ibatis.annotations.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@Validated(Default.class)
public interface CooperationReportMapper {

    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
    @Select("SELECT * FROM cooperation_reports WHERE report_id = #{ report_id }")
    CooperationReport get_by_report_id(@Param("report_id") Long cooperation_report_id );

    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
    @Select("SELECT * FROM cooperation_reports WHERE parent_report_id = #{ parent_report_id }")
    List<CooperationReport> get_by_parent_report_id(@Param("parent_report_id") Long parent_report_id );

//    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
//    @Select("SELECT task_id FROM cooperation_reports WHERE report_id = #{ report_id }")
//    Long get_task_id_by_report_id(@Param("report_id") Long report_id );
//
//    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
//    @Select("SELECT user_id FROM cooperation_reports WHERE report_id = #{ report_id }")
//    Long get_user_id_by_report_id(@Param("report_id") Long report_id );

//    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
//    @Select("SELECT count(1) FROM cooperation_reports WHERE user_id = #{user_id} AND parent_report_id = #{parent_report_id}")
//    Integer is_submitted_by_user_id_and_parent_report_id(@Param("user_id") Long user_id,
//                                                         @Param("parent_report_id")Long parent_report_id);

    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
    @Select("SELECT report_id FROM cooperation_reports WHERE user_id = #{user_id} AND parent_report_id = #{parent_report_id}")
    Long get_report_id_by_user_id_and_parent_report_id(@Param("user_id") Long user_id,
                                                         @Param("parent_report_id")Long parent_report_id);

//    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
//    @Min(value = 1, message = ParamFormatErrorConstant.REPORT_DONT_EXIST_OR_NOT_BELONG_TO_THE_TASK)
//    @Select("SELECT COUNT(1) FROM cooperation_reports WHERE report_id = #{report_id} AND task_id = #{task_id}")
//    Integer assert_report_belong_to_the_task(@Param("report_id")Long report_id, @Param("task_id")Long task_id);

//    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
//    @Max(value = 0, message = ParamFormatErrorConstant.YOU_HAVE_SUBMITTED_THE_REPORT)
//    @Select("SELECT COUNT(1) FROM cooperation_reports WHERE user_id = #{user_id} and parent_report_id = #{parent_report_id}")
//    Integer assert_report_not_submitted(@Param("user_id")Long user_id,@Param("parent_report_id")Long parent_report_id);
//
//
    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
    @Min(value = 1, message = ParamFormatErrorConstant.YOU_HAVE_NOT_SUBMITTED_THE_REPORT)
    @Select("SELECT COUNT(1) FROM cooperation_reports WHERE user_id = #{user_id} and report_id = #{report_id}")
    Integer assert_report_is_submitted(@Param("user_id")Long user_id,@Param("report_id")Long cooperation_report_id);


    @Options(useGeneratedKeys = true, keyProperty = "report_id", keyColumn = "report_id")
    @Insert("INSERT INTO cooperation_reports ( user_id, task_id, defect_explain, " +
            "defect_reproduction_step,test_equipment_information,report_name,report_state, parent_report_id ) " +
            "VALUES ( #{report.user_id}, #{report.task_id},#{report.defect_explain}," +
            "#{report.defect_reproduction_step},#{report.test_equipment_information}, " +
            "#{report.report_name}, #{report.report_state}, #{report.parent_report_id} ) ")
    void insert( @Param("report") CooperationReport report );

//    /**
//     * 删除报告。 注意：会把报告对应的缺陷截图也删除
//     */
//    @Delete( "DELETE FROM cooperation_reports where report_id = #{report_id}; ")
//    void delete_by_report_id(@Param("report_id") Long cooperation_report_id );


    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_EXIST)
    @Select("select MAX(report_id) from cooperation_reports")
    Long max_id();

}
