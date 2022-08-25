package com.dawnflyc.db.mapper;

import java.util.List;
import java.util.Map;

/**
 * curd接口
 */
public interface IDbMapper {

    List<Map<String, Object>> select(String sql, Map<String, Object> params);

    int insert(String sql, Map<String, Object> params);

    int update(String sql, Map<String, Object> params);

    int delete(String sql, Map<String, Object> params);
}
