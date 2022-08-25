package com.dawnflyc.db.service.crud;


import com.dawnflyc.db.ParamHandle;
import com.dawnflyc.db.service.WhereSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询服务
 */
public class SelectService extends WhereSql<SelectService, List<Map<String, Object>>> {

    /**
     * 字段
     */
    private final List<String> fields = new ArrayList<>();
    /**
     * 存储排序
     */
    private final List<String> order = new ArrayList<>();
    /**
     * 存储分组
     */
    private final List<String> group = new ArrayList<>();
    /**
     * 联查
     */
    private String join = "";

    public SelectService(String table, ParamHandle paramHandle) {
        super(table, paramHandle);
    }

    public SelectService(String table) {
        super(table);
    }

    public SelectService() {
    }

    @Override
    public List<Map<String, Object>> execute() {
        sql.append("SELECT ");
        if (fields.size() > 0) {
            sql.append(String.join(",", fields));
        } else {
            sql.append("*");
        }
        sql.append(" FROM ").append(table).append(join == null ? "" : join);
        buildWhere();
        if (group.size() > 0) {
            sql.append(" GROUP BY ").append(String.join(",", group));
        }
        if (order.size() > 0) {
            sql.append(" ORDER BY ").append(String.join(",", order));
        }
        List<Map<String, Object>> result = mapper.select(sql.toString(), getStringParam());
        this.closeParam();
        return result;
    }

    /**
     * 查询一个
     */
    public Map<String, Object> executeGetOne() {
        List<Map<String, Object>> execute = execute();
        if (execute.size() > 0) {
            return execute.get(0);
        }
        return null;
    }

    /**
     * 设置查询字段
     */
    public SelectService field(String field) {
        this.fields.add(field);
        return this;
    }

    /**
     * 排序
     *
     * @param field 字段
     * @param mode  正反排序
     * @return
     */
    public SelectService order(String field, String mode) {
        this.order.add(field + " " + mode);
        return this;
    }

    /**
     * 分组
     */
    public SelectService group(String field) {
        this.group.add(field);
        return this;
    }

    /**
     * limit语句
     *
     * @param offset 从哪行开始
     * @param size   查几个
     * @return
     */
    public SelectService limit(String offset, String size) {
        sql.append(" LIMIT ").append(offset).append(",").append(size);
        return this;
    }

    /**
     * 联查
     *
     * @param mode  左右内
     * @param table 联查表
     * @param on    on
     * @return
     */
    public SelectService join(String mode, String table, String on) {
        this.join += new StringBuilder().append(" ").append(mode).append(" JOIN ").append(table).append(" ON ").append(on).toString();

        return this;
    }


}
