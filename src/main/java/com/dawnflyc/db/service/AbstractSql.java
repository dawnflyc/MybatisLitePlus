package com.dawnflyc.db.service;


import com.dawnflyc.db.ParamHandle;
import com.dawnflyc.db.PreParamManage;
import com.dawnflyc.db.mapper.DbMapperHelper;
import com.dawnflyc.db.mapper.IDbMapper;

import java.util.Map;


/**
 * sql父类
 */
public abstract class AbstractSql<Children extends AbstractSql<Children, R>, R> {


    /**
     * mapper
     */
    public final IDbMapper mapper = DbMapperHelper.dbMapper;
    /**
     * sql
     */
    protected final StringBuffer sql = new StringBuffer();
    /**
     * 参数处理器
     */
    protected final ParamHandle paramHandle;
    /**
     * 预编码参数管理器
     */
    protected final PreParamManage preParamManage = new PreParamManage();
    /**
     * 表名
     */
    protected String table;

    public AbstractSql(String table, ParamHandle paramHandle) {
        this.table = table;
        this.paramHandle = paramHandle;
    }

    public AbstractSql(String table) {
        this.table = table;
        this.paramHandle = ParamHandle.ignoreNull;
    }

    public AbstractSql() {
        this.paramHandle = ParamHandle.ignoreNull;
    }

    /**
     * 转化为正常参数
     *
     * @return
     */
    public Map<String, Object> getStringParam() {
        return preParamManage.getStringParam();
    }

    /**
     * 关闭参数
     */
    public void closeParam() {
        preParamManage.closePreParam();
    }

    /**
     * 获取sql
     *
     * @return
     */
    public String getSql() {
        return this.sql.toString();
    }

    /**
     * 分配预编译参数
     *
     * @param value 数据
     * @return
     */
    protected String allocPreParam(Object value) {
        return preParamManage.allocPreParam(value);
    }

    /**
     * 执行内部sql
     *
     * @return
     */
    public abstract R execute();

}
