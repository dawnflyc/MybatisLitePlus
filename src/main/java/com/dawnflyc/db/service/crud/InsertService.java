package com.dawnflyc.db.service.crud;


import com.dawnflyc.db.ParamHandle;
import com.dawnflyc.db.service.AbstractSql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插入服务
 */
public class InsertService extends AbstractSql<InsertService, Integer> {

    private final Map<String, Object> data = new HashMap<>();

    public InsertService(String table, ParamHandle paramHandle) {
        super(table, paramHandle);
    }

    public InsertService(String table) {
        super(table);
    }

    public InsertService() {
    }

    @Override
    public Integer execute() {
        build();
        Integer result = mapper.insert(sql.toString(), getStringParam());
        this.closeParam();
        return result;
    }

    /**
     * 添加一个
     */
    public InsertService add(String field, Object value) {
        if (!paramHandle.test(field, value, "insert")) {
            data.put(field, value);
        }
        return this;
    }

    /**
     * 添加多个
     */
    public InsertService add(Map<String, Object> map) {
        for (String key : map.keySet()) {
            add(key, map.get(key));
        }
        return this;
    }

    /**
     * 构建语句
     */
    private void build() {
        sql.append("insert into ").append(table);
        sql.append("(");
        List<String> keys = new ArrayList<>(data.keySet());
        sql.append(String.join(",", data.keySet()));
        sql.append(")");
        sql.append("values");
        sql.append("(");
        for (int i = 0; i < keys.size(); i++) {
            sql.append(allocPreParam(data.get(keys.get(i))));
            if (i < keys.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
    }

}
