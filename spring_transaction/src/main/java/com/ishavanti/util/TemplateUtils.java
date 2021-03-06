package com.ishavanti.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public class TemplateUtils {
    private final static String dbDriver = "com.mysql.jdbc.Driver";
    private final static String dbUrl = "jdbc:mysql://localhost:3306/spring_transaction";
    private final static String dbUser = "root";
    private final static String dbPwd = "root";

    private static BasicDataSource dataSource;

    // 静态初始化：创建连接数据源
    static {
        // 创建DBCP简单数据源并初始化相关数据源属性
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPwd);
        // 指定数据库连接池初始连接数
        dataSource.setInitialSize(10);
        // 设定同时向数据库申请的最大连接数
        dataSource.setMaxTotal(50);
        // 设置连接池中保持的最少连接数量
        dataSource.setMinIdle(5);
    }

    public static TransactionTemplate getTransactionTemplate() {
        PlatformTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        return new TransactionTemplate(txManager);
    }

    public static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    public static SimpleJdbcInsert getSimpleJdbcTemplate() {
        return new SimpleJdbcInsert(dataSource);
    }

    /**
     * 获取事务管理器：TransactionManager
     * 根据需要，可以是JDBC、Hibernate等，这里定义JDBC事务管理器
     */
    public static DataSourceTransactionManager getDataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        // 设置数据源：此事务数据源必须和正式事务管理器的数据源一致
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
