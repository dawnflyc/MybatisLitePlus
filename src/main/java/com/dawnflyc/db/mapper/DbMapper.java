package com.dawnflyc.db.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * mapper
 */
public interface DbMapper {
    @Select("${sql}")
    List<Map<String, Object>> select(@Param("sql") String sql, @Param("params") Map<String, Object> params);

    @Insert("${sql}")
    @Options(useGeneratedKeys = true, keyProperty = "ido.id", keyColumn = "id")
    int insert(@Param("sql") String sql, @Param("params") Map<String, Object> params, @Param("ido") Object ido);

    @Update("${sql}")
    int update(@Param("sql") String sql, @Param("params") Map<String, Object> params);

    @Delete("${sql}")
    int delete(@Param("sql") String sql, @Param("params") Map<String, Object> params);
}
