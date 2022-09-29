package com.example.BackendVolatile.mapper.report;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface AlgorithmMapper {

    @Delete("DELETE FROM algorithms")
    void delete_all();

    @Insert("INSERT INTO algorithms (admin_id, algorithm) VALUES (#{admin_id}, #{algorithm})")
    void insert(@Param("admin_id") Long admin_id, @Param("algorithm") String algorithm);

    @Select("SELECT algorithm FROM algorithms WHERE admin_id = #{admin_id}")
    String get_algorithm_by_admin_id(@Param("admin_id") Long admin_id);

    @Select("SELECT algorithm FROM algorithms")
    String get_algorithm();

    @Select("SELECT day from period")
    List<Integer> get_day();

    @Select("DELETE FROM period")
    void delete_day();

    @Insert("INSERT INTO period (day) VALUES (#{day})")
    void insert_day(@Param("day") Integer day);
}
