package com.dawnflyc.db.mapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper获取助手
 */
@Component
@MapperScan("com.dawnflyc.db.mapper")
public class DbMapperHelper {

    public static IDbMapper dbMapper;

    @Autowired
    public void setDbMapper(DbMapper dbMapper) {
        DbMapperHelper.dbMapper = new DbMapperProxy(dbMapper);
    }

}
