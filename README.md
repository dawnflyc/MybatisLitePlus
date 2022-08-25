# Mybatis 轻量级升级

#### 用法:

sql:

```
 Sql.select("select * from user where id = ?",1)
```

链式：

```
Curd.select("user").field("*").where("id",1).execute()
```

#### 使用：

spring 组件扫描 com.dawnflyc.db
