package com.example.BackendVolatile.mapper.report;

import com.example.BackendVolatile.dao.reportDAO.CooperationDefectPicture;
import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import org.apache.ibatis.annotations.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@Validated(Default.class)
public interface CooperationDefectPictureMapper {

    /**
     *
     * @param picture_id： 图片id
     * @return 图片
     */
    @Select("SELECT * FROM cooperation_defect_pictures WHERE picture_id = #{ picture_id }")
    CooperationDefectPicture get_by_picture_id(@Param("picture_id") Long picture_id );

    @NotNull(message = ParamFormatErrorConstant.LIST_CANNOT_BE_NULL)
    @Select("SELECT * FROM cooperation_defect_pictures WHERE report_id = #{report_id}" )
    List<CooperationDefectPicture> get_all_by_report_id(@Param("report_id") Long report_id);

    @Delete("DELETE FROM cooperation_defect_pictures where picture_id = #{picture_id}")
    void delete_by_picture_id(@Param("picture_id") Long picture_id );

    @Delete(" DELETE FROM cooperation_defect_pictures where  report_id = #{report_id} ")
    void delete_all_by_report_id( @Param("report_id") Long report_id);

    /**
     * 插入单张图片
     * @param picture 要插入的图片
     */
    @Options(useGeneratedKeys = true, keyProperty = "picture_id", keyColumn = "picture_id")
    @Insert("INSERT INTO cooperation_defect_pictures ( report_id ,picture_name ,picture_url ) " +
            "VALUES (#{picture.report_id}, #{picture.picture_name},#{picture.picture_url}) ")
    void insert(@Param("picture") CooperationDefectPicture picture );

}
