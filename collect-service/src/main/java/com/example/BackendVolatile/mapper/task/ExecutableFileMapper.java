package com.example.BackendVolatile.mapper.task;

import com.example.BackendVolatile.dao.taskDAO.ExecutableFile;
import org.apache.ibatis.annotations.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ExecutableFileMapper {
    /**
     *
     * @param executable_file_id： 图片id
     * @return 可执行文件
     */
    @Select("SELECT * FROM executable_files WHERE executable_file_id = #{ id }")
    ExecutableFile get_by_executable_file_id(@Param("id") Long executable_file_id );



    /**
     * 得到某个缺陷检测报告的所有图片,并进行分页
     * @param offset
     * @param maxResults
     * @return
     */
    @NotNull
    @Select("SELECT * FROM executable_files WHERE task_id = #{task_id} LIMIT #{offset}, #{maxResults}" )
    List<ExecutableFile> get_all_by_task_id(@Param("task_id") Long task_id ,
                                            @Param("offset") Integer offset, @Param("maxResults") Integer maxResults );


    @NotNull
    @Select("SELECT * FROM executable_files WHERE task_id = #{task_id}" )
    List<ExecutableFile> get_all_by_task_id_without_paging( @Param("task_id") Long task_id);

    @Delete("DELETE FROM executable_files where executable_file_id = #{executable_file_id}")
    void delete_by_executable_file_id(@Param("executable_file_id") Long executable_file_id );

    @Delete(" DELETE FROM executable_files where task_id = #{task_id} ")
    void delete_all_by_task_id( @Param("task_id") Long task_id);

    /**
     * 插入单个文件
     * @param executableFile 要插入的文件
     */
    //    DROP TABLE IF EXISTS executable_files;
//            executable_file_id bigint(20) NOT NULL AUTO_INCREMENT,
//    executable_file_name varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
//    executable_file_url varchar(2083) COLLATE utf8mb4_unicode_ci NOT NULL,
//    task_id bigint(20) NOT NULL,
    @Options(useGeneratedKeys = true, keyProperty = "executable_file_id", keyColumn = "executable_file_id")
    @Insert("INSERT INTO VR_task.executable_files ( task_id ,executable_file_name ,executable_file_url ) " +
            "VALUES (#{executableFile.task_id}, #{executableFile.executable_file_name},#{executableFile.executable_file_url}) ")
    void insert( @Param("executableFile") ExecutableFile executableFile );


}
