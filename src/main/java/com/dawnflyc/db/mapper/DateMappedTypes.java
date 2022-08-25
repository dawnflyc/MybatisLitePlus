package com.dawnflyc.db.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 将LocalDateTime转换位Date
 */
@Component
@MappedTypes(value = {LocalDateTime.class})
public class DateMappedTypes extends BaseTypeHandler<Date> {


    @Autowired
    public void register(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().register(this);
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, new Timestamp(parameter.getTime()));
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnName);
        if (ts == null) {
            return null;
        }
        return new Date(ts.getTime());
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnIndex);
        if (ts == null) {
            return null;
        }
        return new Date(ts.getTime());
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp ts = cs.getTimestamp(columnIndex);
        if (ts == null) {
            return null;
        }
        return new Date(ts.getTime());
    }
}
