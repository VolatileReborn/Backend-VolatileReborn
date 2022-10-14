package com.example.BackendVolatile.mapper.task;

import com.example.BackendVolatile.dao.taskDAO.RequirementDescriptionFile;
import org.apache.ibatis.annotations.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface RequirementDescriptionFileMapper {

//    requirement_description_files;
//            file_id
//    file_name
//    file_url
//    task_id

    /**
     *
     * @param file_id： 图片id
     * @return 可执行文件
     */
    @Select("SELECT * FROM requirement_description_files WHERE file_id = #{ id }")
    RequirementDescriptionFile get_by_file_id(@Param("id") Long file_id );



    /**
     * 得到某个缺陷检测报告的所有图片,并进行分页
     * @param offset
     * @param maxResults
     * @return
     */
    @NotNull
    @Select("SELECT * FROM requirement_description_files WHERE task_id = #{task_id} LIMIT #{offset}, #{maxResults}" )
    List<RequirementDescriptionFile> get_all_by_task_id(
            @Param("task_id") Long task_id , @Param("offset") Integer offset, @Param("maxResults") Integer maxResults );

    @NotNull
    @Select("SELECT * FROM VR_task.requirement_description_files WHERE task_id = #{task_id}" )
    List<RequirementDescriptionFile> get_all_by_task_id_without_paging( @Param("task_id") Long task_id);

    @Delete("DELETE FROM requirement_description_files where file_id = #{file_id}")
    void delete_by_file_id(@Param("file_id") Long file_id );

    @Delete(" DELETE FROM requirement_description_files where task_id = #{task_id} ")
    void delete_all_by_task_id( @Param("task_id") Long task_id);

    /**
     * 插入单个文件
     * @param requirementDescriptionFile 要插入的文件
     */

    @Options(useGeneratedKeys = true, keyProperty = "file_id", keyColumn = "file_id")
    @Insert("INSERT INTO VR_task.requirement_description_files ( task_id ,file_name ,file_url ) " +
            "VALUES (#{requirementDescriptionFile.task_id}, #{requirementDescriptionFile.file_name},#{requirementDescriptionFile.file_url}) ")
    void insert( @Param("requirementDescriptionFile") RequirementDescriptionFile requirementDescriptionFile );


}
