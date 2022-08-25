package com.dawnflyc.db.service.crud;


import com.dawnflyc.db.ParamHandle;
import com.dawnflyc.db.service.WhereSql;

/**
 * 删除服务
 */
public class DeleteService extends WhereSql<DeleteService, Integer> {


    public DeleteService(String table, ParamHandle paramHandle) {
        super(table, paramHandle);
    }

    public DeleteService(String table) {
        super(table);
    }

    public DeleteService() {
    }

    @Override
    public Integer execute() {
        sql.append("DELETE FROM ").append(table);
        buildWhere();
        int result = mapper.delete(sql.toString(), getStringParam());
        this.closeParam();
        return result;
    }
}
